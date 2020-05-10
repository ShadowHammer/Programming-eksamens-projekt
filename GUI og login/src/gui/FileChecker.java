package gui;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.ArrayList;

public class FileChecker {

    //Klassevariabler til at huse nogle variabler der skal bruges
    private String newPattern = "";
    private ArrayList<String> filtypeListe = new ArrayList<>();
    
    //Metode til at lave listen af godkendte filtyper
    public ArrayList fileSelecter() {
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        //Ryder listen af filtyper, så den er tom fra starten hver gang
        filtypeListe.clear();
        
        System.out.println("Vælg filtype! \nSkriv ad flere gange for at " + 
                            "tillade flere slags filtyper. Skriv !slut "  +
                            "for at bekræfte.");
        
        //Loop der bliver ved med at spørge hvilke filtyper, der skal i listen
        while (counter == 0) {
            String tempFiltype = sc.next();           
            
            if (tempFiltype.equals("!slut")) {
                counter = 1;
            } else {
                filtypeListe.add(tempFiltype);
            }
        }
        
        System.out.println(filtypeListe);
        //Returnere til sidst den færdige liste af filtyper
        return filtypeListe;
    
}
    
    //Metode der tjekker om en af de indtastede filtyper er godkendt
    //De to argumenter er listen af godkendte filtyper og navnet på aflveringen
    public boolean checker(ArrayList<String> filtypeListe, String aflevering) {
        boolean validFiltype = false;
        
        //Løber listen igennem med hver filtype, og tjekker dem hver for sig
        for (String type : filtypeListe) {
            //Indsætter en filtype fra listen og laver et RegEx-pattern
            newPattern = "\\." + type + "\\z";
            Pattern p = Pattern.compile(newPattern);
            Matcher m = p.matcher(aflevering);
            
            //Tjekker om RegEx-mønstret passede, og sætter ValidFiltype = true, hvis det er
            if (m.find() || (validFiltype == true)) {
                validFiltype = true;
            } else {
                validFiltype = false;
            }
        }
        
        //Returnere true, hvis en af filtyperne er godkendte
        return validFiltype;
    }

    //Get og set metoder
    public String getNewPattern() {
        return newPattern;
    }

    public void setNewPattern(String newPattern) {
        this.newPattern = newPattern;
    }

    public ArrayList<String> getFiltypeListe() {
        return filtypeListe;
    }

    public void setFiltypeListe(ArrayList<String> filtypeListe) {
        this.filtypeListe = filtypeListe;
    }
    
    
}
