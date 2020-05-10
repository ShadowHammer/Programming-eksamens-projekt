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
import javax.swing.JTextField;

/**
 *
 * @author Tobias
 * @author William
 */
public class ElevPopup extends JFrame{
    // Her laver jeg instancer af de forskellige komponenter jeg bruger
    private JLabel fil,elevNavn,overskrift;
    private JTextField elevField, filField;
    private JPanel popupPanel;
    private JButton aflever;
    private DatabaseHandler DB = new DatabaseHandler();
    
    public ElevPopup(){
        
    }
    // opretter komponenterne i vinduet
    public void koer(int selectedRow){
        
    
        setTitle("Ludus kopi - Elev Popup");
        setSize(300,205);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        // Her sætter jeg variablerne
        fil = new JLabel();
        elevNavn = new JLabel();
        overskrift = new JLabel();

        elevField = new JTextField();
        filField = new JTextField();

        aflever = new JButton();

        popupPanel = new JPanel();
        add(popupPanel);
        popupPanel.setLayout(null);

        popupPanel.add(fil);
        popupPanel.add(elevNavn);
        popupPanel.add(elevField);
        popupPanel.add(filField);

        
        popupPanel.add(aflever);
        popupPanel.add(overskrift);
        
        //Her indsætter jeg komponenterne forskellige steder i vinduet efter kordinater
        overskrift.setBounds(100, 10, 150, 20);
        overskrift.setText("Aflever aflevering");

        elevNavn.setBounds(50, 30, 200, 20);
        elevNavn.setText("Skriv elevens navn");
        elevField.setBounds(50, 50, 200, 25);
        elevField.setToolTipText("f.eks.: William");

        fil.setBounds(50, 70, 200, 25);
        fil.setText("Skriv stien til filen du vil indsætte");
        filField.setBounds(50, 90, 200, 25);
        filField.setToolTipText("Den komplette sti til filen");



        aflever.setBounds(100,120,100,30);
        aflever.setText("aflever");
        

        aflever.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    String navn = elevField.getText();
                    String filen = filField.getText();
                    
                    //System.out.println(faget);
                    System.out.println(filen);
                    DB.handIn(selectedRow, navn, filen);
                }
            });
}
}
