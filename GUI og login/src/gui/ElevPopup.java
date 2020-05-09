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
 */
public class ElevPopup extends JFrame{
    private JLabel fil,fagID,overskrift;
    private JTextField fagField, filField;
    private JPanel popupPanel;
    private JButton aflever;
    
    public ElevPopup(){
        
    }
    public void koer(){
        
    
        setTitle("Ludus kopi - Elev Popup");
        setSize(300,205);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        fil = new JLabel();
        fagID = new JLabel();
        overskrift = new JLabel();

        fagField = new JTextField();
        filField = new JTextField();

        aflever = new JButton();

        popupPanel = new JPanel();
        add(popupPanel);
        popupPanel.setLayout(null);

        popupPanel.add(fil);
        popupPanel.add(fagID);
        popupPanel.add(fagField);
        popupPanel.add(filField);

        
        popupPanel.add(aflever);
        popupPanel.add(overskrift);

        overskrift.setBounds(100, 10, 150, 20);
        overskrift.setText("Aflever aflevering");

        fagID.setBounds(50, 30, 200, 20);
        fagID.setText("Skriv faget som aflevering er i");
        fagField.setBounds(50, 50, 200, 25);
        fagField.setToolTipText("f.eks.: Matematik");

        fil.setBounds(50, 70, 200, 25);
        fil.setText("Skriv stien til filen du vil indsætte");
        filField.setBounds(50, 90, 200, 25);
        filField.setToolTipText("Den komplette sti til filen");



        aflever.setBounds(100,120,100,30);
        aflever.setText("aflever");
        

        aflever.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    String faget = "";
                    faget = fagField.getText();

                    String filen = "";
                    filen = filField.getText();
                    
                    System.out.println(faget);
                    System.out.println(filen);

                }
            });
}
}
