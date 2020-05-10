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
public class DownloadElevPopup extends JFrame{
    // Her laver jeg instancer af de forskellige komponenter jeg bruger
    private JLabel fag,overskrift,fil;
    private JTextField fagField,filField;
    private JPanel popupPanel;
    private JButton download;
    private DatabaseHandler DB = new DatabaseHandler();

    public DownloadElevPopup(){
        
}
    // opretter komponenterne i vinduet
    public void koer(int selectedRow){
        
        setTitle("Ludus kopi - Elev Popup");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        // Her sætter jeg variablerne
        fil = new JLabel();
        filField = new JTextField();
        fag = new JLabel();
        fagField = new JTextField();
        overskrift = new JLabel();
        

        download = new JButton();

        popupPanel = new JPanel();
        add(popupPanel);
        popupPanel.setLayout(null);

        popupPanel.add(fag);
        popupPanel.add(fagField);
        
        popupPanel.add(download);
        popupPanel.add(overskrift);
        
        popupPanel.add(fil);
        popupPanel.add(filField);
        
        //Her indsætter jeg komponenterne forskellige steder i vinduet efter kordinater
        fil.setBounds(50, 10, 200, 20);
        fil.setText("Skriv stien til download sted");
        filField.setBounds(50, 30, 200, 25);
        filField.setToolTipText("Skriv hele stien til den mappe filen skal ligge i");

        download.setBounds(100,60,100,30);
        download.setText("Download");
        

        download.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*String faget = "";
                    faget = fagField.getText();*/
                    
                    String filen = "";
                    filen = filField.getText();
                    
                    
                    DB.downloadFile(selectedRow, filen, true);
                    
                    //System.out.println(faget);
                    System.out.println(filen);

                }
            });
    }}