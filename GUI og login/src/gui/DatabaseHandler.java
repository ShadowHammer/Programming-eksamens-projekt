
package gui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;







/**
 *
 * @author willy
 */
public class DatabaseHandler {

    public String fileExtension = "";

    /*public DatabaseHandler() {
        conn = this.conn;
        this.stmt = null;
    }*/

    
    private Connection connect(){
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:/Users/willy/Documents/NetBeansProjects/Programming-eksamens-projekt/Database/DataStorage.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected");
        }catch(SQLException e){
            System.out.println("Fejl" + e.getMessage());
        }
        return conn;
    }
    
    
    private byte[] readFile (String filePATH){
        ByteArrayOutputStream bos = null;
        
        for (int i = filePATH.length()-1; i<filePATH.length();i--){
            
            if(filePATH.charAt(i)=='.'){
                fileExtension = filePATH.substring(i);
                break;
            }
        }

        
        try{
            File file = new File(filePATH);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int i=0; (i = fis.read(buffer)) != -1;){
                bos.write(buffer, 0, i);
            }
            
        }catch(FileNotFoundException e){
            System.err.println("Fejl "+ e);
        }catch (IOException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bos != null ? bos.toByteArray() : null;
    }
    

    /**
     *
     * @param fag
     * @param dato
     * @param beskrivelsePATH
     */
    public void uploadAssignment(String fag, String dato, String beskrivelsePATH){
        boolean delivered = false;
        String sql = "INSERT INTO assignments (fag,dato,beskrivelse,ext,afleveret) VALUES ( ?,?,?,?,?);";
        
        try (Connection conn = connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, fag);
            stmt.setString(2, dato);
            stmt.setBytes(3, readFile(beskrivelsePATH));
            stmt.setString(4, fileExtension);
            stmt.setBoolean(5, delivered);
            
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate > 0)
                System.out.println("fedt");
            
        } catch (SQLException ex) {
            System.out.println("Fejl " + ex.getMessage());
        
        }
    }
    
    /**
     *
     * @param assignmentID
     * @param assignmentPATH
     */
    public void handIn(int assignmentID, String pupil, String assignmentPATH){
        String sql = "INSERT INTO besvarelser (elev, besvarelse, ext, opgaveID) VALUES (?,?,?,?);";
        String sql2= "INSERT INTO assignments (afleveret) VALUES (?);";
        PreparedStatement stmt;
        
        try(Connection conn = connect()){
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1,pupil);
            stmt.setBytes(2,readFile(assignmentPATH));
            stmt.setString(3,fileExtension);
            stmt.setInt(4,assignmentID);
            stmt.execute();
            
            stmt = conn.prepareStatement(sql2);
            stmt.setBoolean(1, true);
            stmt.execute();
            
            conn.close();
        }catch (SQLException ex) {
            System.out.println("Fejl " + ex.getMessage());
        }
    }
    


    /**
     *
     * @param assignmentID
     * @param isDescribtion
     * @param destPATH This function reads and deliveres a file which is either an assignment or
    an answer for an assignment
     */
    public void downloadFile(int assignmentID, String destPATH, boolean isDescribtion){
        String column;
        String sql;
        String fileName="Opgave_Beskrivelse";
        ResultSet rs = null;
        FileOutputStream fos = null;
        PreparedStatement stmt = null;
        
        if (isDescribtion){
            sql = "SELECT beskrivelse, ext FROM assignments WHERE id = ?";
            column = "beskrivelse";
        }else {
            sql = "SELECT elev, besvarelse, ext FROM besvarelser WHERE opgaveID = ?";
            column = "besvarelse";
        }
        
        try(Connection conn = connect()){
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,assignmentID);
            rs = stmt.executeQuery();
            if(!isDescribtion){
                downloadAnswers(destPATH,rs);
            }else{
                File file = new File(destPATH + fileName + rs.getString("ext"));
                fos = new FileOutputStream(file);
                System.out.println("Writing BLOB to file " + file.getAbsolutePath());
                InputStream input = rs.getBinaryStream(column);    
            
                while (rs.next()) {
                    byte[] buffer = new byte[1024];
                    while (input.read(buffer)>0){
                        fos.write(buffer);
                    }
                }
                
            conn.close();
            }
        }catch(SQLException  | IOException e){
            System.out.println("fejl "+ e.getMessage());
        }
    }
    
    
    
    private void downloadAnswers( String destPATH, ResultSet rs){
        FileOutputStream fos = null;
        
        try(Connection conn = connect()){          
            while (rs.next()) {
                File file = new File(destPATH + rs.getString("elev")+rs.getString("ext"));
                fos = new FileOutputStream(file);
                System.out.println("Writing BLOB to file " + file.getAbsolutePath());
                InputStream input = rs.getBinaryStream("besvarelse");
                byte[] buffer = new byte[1024];
                
                while (input.read(buffer)>0){
                    fos.write(buffer);
                }
            }
            
            conn.close();
        }catch(SQLException  | IOException e){
            System.out.println("fejl "+ e.getMessage());
        }
    }
    
       
   
}
