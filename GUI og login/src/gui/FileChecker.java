/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eksamensprojekt.fake.ludus;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Lasse
 */
public class FileChecker {

    private String newPattern = "";
    private ArrayList<String> filtypeListe = new ArrayList<>();
    
    public ArrayList fileSelector2() {
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        filtypeListe.clear();
        
        System.out.println("Vælg filtype! \nSkriv ad flere gange for at " + 
                            "tillade flere slags filtyper. Skriv !slut "  +
                            "for at bekræfte.");
        
        while (counter == 0) {
            String tempFiltype = sc.next();           
            
            if (tempFiltype.equals("!slut")) {
                counter = 1;
            } else {
                filtypeListe.add(tempFiltype);
            }
        }
        System.out.println(filtypeListe);
        return filtypeListe;
    
}
    
    public boolean checker2(ArrayList<String> filtypeListe, String teststring) {
        boolean validFiltype = false;
        
        for (String type : filtypeListe) {
            newPattern = "\\." + type + "\\z";
            Pattern p = Pattern.compile(newPattern);
            Matcher m = p.matcher(teststring);
            
            if (m.find() || (validFiltype == true)) {
                validFiltype = true;
            } else {
                validFiltype = false;
            }
        }
        
        return validFiltype;
    }
    
    /*
    public void fileSelector() {
        System.out.println("Vælg filtype:");
        
        String filtype = sc.next();
        newPattern = "\\." + filtype + "\\z";
        p = Pattern.compile(newPattern);
        
        System.out.println(newPattern);
    }
    
    
    
    public boolean checker(String teststring){
        Matcher m = p.matcher(teststring);
        
        return m.find();
    }

    */
    
    
}
