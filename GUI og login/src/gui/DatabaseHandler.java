
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
import java.sql.Statement;
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
    
/*    public void disconnect(){
        if (null != this.conn){
            try{
                if(conn != null){
                    conn.close();
                    System.out.println("closed");
                }
            }catch(SQLException e){
                System.out.println("Fejl" + e.getMessage());
            }
        }
    }*/
    
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
    public void handIn(int assignmentID, String assignmentPATH){
        String sql = "INSERT INTO besvarelser (besvarelse, ext, opgaveID) VALUES (?,?,?);";
        String sql2= "INSERT INTO assignments (afleveret) VALUES (?);";
        PreparedStatement stmt;
        
        try(Connection conn = connect()){
            stmt = conn.prepareStatement(sql);
            
            stmt.setBytes(1,readFile(assignmentPATH));
            stmt.setString(2,fileExtension);
            stmt.setInt(3,assignmentID);
            stmt.execute();
            
            stmt = conn.prepareStatement(sql2);
            stmt.setBoolean(1, true);
            stmt.execute();
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
        ResultSet rs = null;
        FileOutputStream fos = null;
        PreparedStatement stmt = null;
        
        if (isDescribtion){
            sql = "SELECT beskrivelse FROM assignments WHERE id = ?";
            column = "beskrivelse";
        }else {
            sql = "SELECT besvarelse FROM besvarelser WHERE opgaveID = ?";
            column = "besvarelse";
        }
        
        try(Connection conn = connect()){
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,assignmentID);
            rs = stmt.executeQuery();
            
            File file = new File(destPATH);
            fos = new FileOutputStream(file);
            
            System.out.println("Writing BLOB to file " + file.getAbsolutePath());
            while (rs.next()) {
                
                InputStream input = rs.getBinaryStream(column);
                byte[] buffer = new byte[1024];
                while (input.read(buffer)>0){
                    fos.write(buffer);
                }
            }
        }catch(SQLException  | IOException e){
            System.out.println("fejl "+ e.getMessage());
        } finally {
            try{
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (fos != null)
                    fos.close();
            
            }catch(SQLException  | IOException e){
                System.out.println("fejl "+ e.getMessage());
            }
        }
    }
}
