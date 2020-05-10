/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class Loginsystem {

    /**
     * @param args the command line arguments
     */
    //Jeg har brugt følgende link til hjælp med at lave kryptering af kodeord
    //https://www.javaguides.net/2020/02/java-sha-256-hash-with-salt-example.html
     
    // jeg laver 3 arraylists
    public static ArrayList<byte[]> saltArray = new ArrayList<>();
    public static ArrayList<String>passwordArray = new ArrayList<>();
    public static ArrayList<byte[]>passwords = new ArrayList<>();
    
    public static void login(String[] args) {
        
        try {
            passwordArray.add(getPassword());
            getSalt();
            getSikreKodeord(passwordArray.get(0), saltArray.get(0));
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Loginsystem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //Det tager passwordet og hasher det til at starte med 
    public static byte[] getSikreKodeord(String password, byte[] salt){
        try {
            MessageDigest kryptering = MessageDigest.getInstance("SHA-256");
            kryptering.update(salt);
            byte[] bytes = kryptering.digest(password.getBytes());
            passwords.add(bytes);
            return bytes;
        } catch (NoSuchAlgorithmException ex) { 
            Logger.getLogger(Loginsystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    //her laver jeg saltet som bruges til kodeordet
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[64];
        random.nextBytes(salt);
        saltArray.add(salt);
        return salt;
    }
    //Her kommer kodeordet fra kodeordet
    private static String getPassword() {
        
        String x = "1234"; 
        return x;
    }
}
    

