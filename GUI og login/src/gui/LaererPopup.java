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
public class LaererPopup extends JFrame {
    private JLabel fil,dato,fag,overskrift,filtype;
    private JTextField fagField,datoField, filField,filtypeField;
    private JPanel popupPanel;
    private JButton opret;
    private DatabaseHandler DB = new DatabaseHandler();
    
    public LaererPopup(){
        
    }
    public void koer(){
        
    
        setTitle("Ludus kopi - Lærer Popup");
        setSize(300,345);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        fil = new JLabel();
        dato = new JLabel();
        fag = new JLabel();
        overskrift = new JLabel();
        filtype = new JLabel();

        fagField = new JTextField();
        datoField = new JTextField();
        filField = new JTextField();
        filtypeField = new JTextField();

        opret = new JButton();

        popupPanel = new JPanel();
        add(popupPanel);
        popupPanel.setLayout(null);

        popupPanel.add(fil);
        popupPanel.add(dato);
        popupPanel.add(fag);
        popupPanel.add(fagField);
        popupPanel.add(datoField);
        popupPanel.add(filField);
        popupPanel.add(filtypeField);
        popupPanel.add(filtype);
        
        popupPanel.add(opret);
        popupPanel.add(overskrift);

        overskrift.setBounds(100, 10, 150, 20);
        overskrift.setText("Opret aflevering");


        fag.setBounds(50, 30, 200, 20);
        fag.setText("Skriv faget som aflevering er i");
        fagField.setBounds(50, 50, 200, 25);
        fagField.setToolTipText("f.eks.: Matematik");


        dato.setBounds(50, 80, 200, 20);
        dato.setText("Skriv datoen for afleveringen");
        datoField.setBounds(50, 100, 200, 25);
        datoField.setToolTipText("f.eks.: 11-05-2020");

        fil.setBounds(50, 130, 200, 25);
        fil.setText("Skriv stien til filen du vil indsætte");
        filField.setBounds(50, 150, 200, 25);
        filField.setToolTipText("Den komplette sti til filen");
        
        
        filtype.setBounds(50,180,200,25);
        filtype.setText("Skriv de tiladte filtyper");
        filtypeField.setBounds(50, 200, 200, 25);
        filtypeField.setToolTipText("f.eks.: pdf,docx,png");


        opret.setBounds(100,240,100,30);
        opret.setText("Opret");
        

        opret.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    String faget = "";
                    faget = fagField.getText();
                    
                    String datoen = "";
                    datoen = datoField.getText();

                    String filen = "";
                    filen = filField.getText();
                    
                    String filtyper = "";
                    filtyper = filtypeField.getText();
                    
                    DB.uploadAssignment(faget, datoen, filen);
                    
                    System.out.println(faget);
                    System.out.println(datoen);
                    System.out.println(filen);
                    System.out.println(filtyper);
                }
            });
    
}
}
