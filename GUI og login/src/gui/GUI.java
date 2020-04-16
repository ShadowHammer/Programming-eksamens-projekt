/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;

/**
 *
 * @author Tobias
 */
public class GUI {

    
    public static void guiMain() {
        // TODO code application logic here
        int x = 900;
        int y = 800;
//        JFrame laerer = new Laerer(x,y);
        //Elev elev = new Elev(x,y);
        //elev.setTitle("Ludus kopi - Elever");
        //elev.setSize(x,y);
        //elev.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //elev.setVisible(true);
//        laerer.setTitle("Ludus kopi - Lærer");
//        laerer.setSize(x,y);
//        laerer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        laerer.setVisible(true);
        LoginPage login = new LoginPage(x,y);
        login.setTitle("Ludus kopi - Login");
        login.setSize(x,y);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);
    }
    
}
