/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Tobias
 */
public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        DatabaseHandler DB = new DatabaseHandler();
        
        while(true){
            String str = sc.next();

            if (str.equals("write")){
                DB.uploadAssignment( "Matematik", "5/5/2020","C:\\Users\\willy\\Documents\\NetBeansProjects\\Programming-eksamens-projekt\\Beskrivelser\\y.docx");
            }else if (str.equals("read")){
                DB.downloadFile(1, "C:/Users//willy//Documents/NetBeansProjects/Programming-eksamens-projekt/Beskrivelser/besvarelser.pdf",false);    
            }else if (str.equals("handin")){
                DB.handIn(1, "C:\\Users\\willy\\Documents\\NetBeansProjects\\Programming-eksamens-projekt\\Beskrivelser\\mat aflevering.pdf");
            }else if (str.equals("close")){
                break;
            }
        }
    }
}
