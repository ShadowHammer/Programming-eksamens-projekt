/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    
    private JPanel panelLogin;
    private JLabel overskrift, labUser, labPass;
    private JButton loginKnap;
    private JPasswordField password;
    private JTextField username;
    

    public LoginPage(int x, int y){
        creatComponents(x,y);
        
    }
    
    private void creatComponents(int x, int y) {

        
        
        panelLogin = new JPanel();
        add(panelLogin);
        panelLogin.setLayout(null);
        overskrift = new JLabel();
        labUser = new JLabel();
        labPass = new JLabel();
        username = new JTextField();
        password = new JPasswordField();
        loginKnap = new JButton("Login");
        
        panelLogin.add(overskrift);
        overskrift.setText("Skriv dit bruger navn og kode");
        overskrift.setBounds(x/2-80, 100, 250, 20);
        
        panelLogin.add(labUser);
        labUser.setText("Username:");
        labUser.setBounds(x/2-135, 150, 250, 20);
        
        panelLogin.add(labPass);
        labPass.setText("Password:");
        labPass.setBounds(x/2-135, 180, 250, 20);
        
        panelLogin.add(username);
        username.setBounds(x/2-70, 150, 150, 20);
        
        panelLogin.add(password);
        password.setBounds(x/2-70, 180, 150, 20);
        
        panelLogin.add(loginKnap);
        loginKnap.setBounds((x/2-70), 225, 150, 50);
        
        
        // kode taget fra https://www.javatpoint.com/java-jbutton for at bruge action listener
        loginKnap.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Brugernavn og kode ord til en lærer
                String passInCodeLære = "1234";
                Object userLære = "Hej";
                
                //Brugernavn og kodeor til en elev
                String passInCodeElev = "4321";
                Object userElev = "Jeg";
                
                //Henter det som man har skrevet ind i password feltet
                char[] pass = password.getPassword();

                //tester om loginnet passer med lærens login oplysninger
                if (String.valueOf(pass).equals(passInCodeLære) && username.getText().equals(userLære)){
                    //den åbner det nye vindue og lukker loginsiden
                    Laerer laerer = new Laerer();
                    laerer.creatComponents(x,y);
                    dispose();
                }
                //tester om loginnet passer med elevens login oplysninger
                if (String.valueOf(pass).equals(passInCodeElev) && username.getText().equals(userElev)){
                    //den åbner det nye vindue og lukker loginsiden
                    Elev elev = new Elev();
                    elev.creatComponents(x,y);
                    dispose();
                }
            }
        
        });
         
     
}
}
