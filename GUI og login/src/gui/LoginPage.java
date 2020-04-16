/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author Tobias
 */
public class LoginPage extends JFrame{

    private JFrame frame;
    private JPanel panelLogin;
    private JLabel label1;
    private JButton loginKnap;
    private JPasswordField password;
    private JTextField username;
    

    public LoginPage(int x, int y){
        creatComponents(x,y);
    }
    
    private void creatComponents(int x, int y) {

        frame = new JFrame();
        panelLogin = new JPanel();
        panelLogin.setLayout(null);
        label1 = new JLabel("Hej");
        username = new JTextField();
        password = new JPasswordField();
        loginKnap = new JButton();
        panelLogin.add(label1);
//        label1.setText("Skriv dit bruger navn og kode");
        panelLogin.add(username);
        panelLogin.add(password);
//        password.setBounds(x/2, (y/2), 50, 50);
        panelLogin.add(loginKnap);
//        loginKnap.setBounds((x/2)-100, (y/2)+50, 50, 50);

        frame.add(panelLogin);
        
        
     
}
}
