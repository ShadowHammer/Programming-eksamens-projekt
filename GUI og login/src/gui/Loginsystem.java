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
    public static ArrayList<byte[]> saltArray = new ArrayList<>();
    public static ArrayList<String>passwordArray = new ArrayList<>();
    public static ArrayList<byte[]>passwords = new ArrayList<>();
    
    public static void login(String[] args) {
        
        try {
            passwordArray.add(getPassword());
            getSalt();
            getSecurePassword(passwordArray.get(0), saltArray.get(0));
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Loginsystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(passwordArray);
        System.out.println(saltArray);
        System.out.println(passwords);

    }
    public static byte[] getSecurePassword(String password, byte[] salt){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            passwords.add(bytes);
            return bytes;
        } catch (NoSuchAlgorithmException ex) { 
            Logger.getLogger(Loginsystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[64];
        random.nextBytes(salt);
        saltArray.add(salt);
        return salt;
    }

    private static String getPassword() {
        
        String x = "1234"; 
        return x;
    }
}
    

