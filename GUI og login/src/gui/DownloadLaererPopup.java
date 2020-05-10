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
public class DownloadLaererPopup extends JFrame{
    private JLabel fil,dato,fag,overskrift,filtype;
    private JTextField fagField,datoField, filField,filtypeField;
    private JPanel popupPanel;
    private JButton download;


    public DownloadLaererPopup(){

}
    
    public void koer(){
        
    
        setTitle("Ludus kopi - Lærer Popup");
        setSize(300,155);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);


        fag = new JLabel();
        overskrift = new JLabel();
        fagField = new JTextField();

        download = new JButton();

        popupPanel = new JPanel();
        add(popupPanel);
        popupPanel.setLayout(null);

        popupPanel.add(fag);
        popupPanel.add(fagField);
        
        popupPanel.add(download);
        popupPanel.add(overskrift);

        overskrift.setBounds(100, 10, 150, 20);
        overskrift.setText("Opret aflevering");


        fag.setBounds(50, 30, 200, 20);
        fag.setText("Skriv faget som aflevering er i");
        fagField.setBounds(50, 50, 200, 25);
        fagField.setToolTipText("f.eks.: Matematik");

        download.setBounds(100,80,100,30);
        download.setText("Download");
        

        download.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    String faget = "";
                    faget = fagField.getText();

                    System.out.println(faget);

                }
            });
    }}