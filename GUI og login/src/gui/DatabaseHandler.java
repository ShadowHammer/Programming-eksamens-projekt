
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;







/**
 *
 * @author willy
 */
public class DatabaseHandler {

    public String fileExtension = "";
    public String fileName = "";

    public DatabaseHandler() {

    }

    //This method is used to establish a connection to the database
    private Connection connect(){
        Connection conn = null;
        try {
            /*
            *IMPORTANT* Set the value of the string url to the location of the 
            database which is located in the project folder. example
            jdbc:sqlite:your path;
            */
            String url = "jdbc:sqlite:C:/Users/willy/Documents/NetBeansProjects/Programming-eksamens-projekt/Database/DataStorage.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected");
        }catch(SQLException e){
            System.out.println("Fejl" + e.getMessage());
        }
        return conn;
    }
    
    //this method reads a file from and writes it to a byteArray which is uploaded to the database
    private byte[] readFile (String filePATH){
        ByteArrayOutputStream bos = null;
        
        for (int i = filePATH.length()-1; i >= 0; i--){
            
            if(filePATH.charAt(i)=='.'){
                fileExtension = filePATH.substring(i);
                for (int j = i; j < filePATH.length(); j--){
                    if (filePATH.charAt(j) == '/' || (int) filePATH.charAt(j) == 92){
                        fileName = filePATH.substring(j+1, i);
                        break;
                    }
                }
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
     * this method uploads an assignment to the assignments table
     */
    public void uploadAssignment(String fag, String dato, String beskrivelsePATH){
        boolean delivered = false;
        String sql = "INSERT INTO assignments (fag,dato,beskrivelse,filnavn,ext,afleveret) VALUES ( ?,?,?,?,?,?);";
        
        try (Connection conn = connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, fag);
            stmt.setString(2, dato);
            stmt.setBytes(3, readFile(beskrivelsePATH));
            stmt.setString(4, fileName);
            stmt.setString(5, fileExtension);
            stmt.setBoolean(6, delivered);
            
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
     * the handIn method takes an assignmentID a name and a PATH to Download folder
     */
    public void handIn(int assignmentID, String pupil, String assignmentPATH){
        String sql = "INSERT INTO besvarelser (elev, besvarelse, filnavn, ext, opgaveID) VALUES (?,?,?,?,?);";
        String sql2= "UPDATE assignments SET afleveret = ? WHERE ID = ?;";
        PreparedStatement stmt;
        
        try(Connection conn = connect()){
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1,pupil);
            stmt.setBytes(2,readFile(assignmentPATH));
            stmt.setString(3,fileName);
            stmt.setString(4,fileExtension);
            stmt.setInt(5,assignmentID);
            stmt.execute();
            
            stmt = conn.prepareStatement(sql2);
            stmt.setBoolean(1, true);
            stmt.setInt(2, assignmentID);
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
        String name = "Opgave_Beskrivelse";
        ResultSet rs = null;
        FileOutputStream fos = null;
        PreparedStatement stmt = null;
        
        if (isDescribtion){
            sql = "SELECT beskrivelse, ext FROM assignments WHERE id = ?";
            column = "beskrivelse";
        }else {
            sql = "SELECT elev, besvarelse, filnavn, ext FROM besvarelser WHERE opgaveID = ?";
            column = "besvarelse";
        }
        
        try(Connection conn = connect()){
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,assignmentID);
            rs = stmt.executeQuery();
            if(!isDescribtion){
                downloadAnswers(destPATH,rs);
            }else{
                File file = new File(destPATH+ "/" + name + rs.getString("ext"));
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
            System.out.println("Download Failed due to: "+ e.getMessage());
        }
    }
    
    
    
    private void downloadAnswers( String destPATH, ResultSet rs){
        FileOutputStream fos = null;
        
        try(Connection conn = connect()){          
            while (rs.next()) {
                File file = new File(destPATH + rs.getString("elev")+rs.getString("filnavn")+rs.getString("ext"));
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
            System.out.println("Download failed due to: "+ e.getMessage());
        }
    }
    
    public ArrayList<Object[]> getAssignmentTable(){
        String sql = "SELECT fag, dato, filnavn, ext, afleveret FROM assignments";
        ArrayList<Object[]> result = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        
        try(Connection conn = connect()){
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            Object[] headlineRow = {"Fag","Afleverings dato", "Opgave beskrivelse", "Aflevering status"};
            result.add(headlineRow);
            while(rs.next()){
                Object [] row = {rs.getString("fag"), rs.getString("dato"), rs.getString("filnavn")+rs.getString("ext"),rs.getBoolean("afleveret")};
                result.add(row);
            
            }
            
        }catch(SQLException e){
            System.out.println("fetch of table failed: "+ e.getMessage());
        }
        return result;
    }
       
   
}
