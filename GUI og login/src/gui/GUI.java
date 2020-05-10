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

    
    public void guiMain() {
        // TODO code application logic here
        int x = 900;
        int y = 800;
        LoginPage login = new LoginPage(x,y);
        login.setTitle("Ludus kopi - Login");
        login.setSize(x,y);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);
    }
    
}
