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
    private JLabel fag,overskrift,fil;
    private JTextField fagField,filField;
    private JPanel popupPanel;
    private JButton download;
    private DatabaseHandler DB = new DatabaseHandler();

    public DownloadElevPopup(){
        
}
    
    public void koer(int selectedRow){
        
        setTitle("Ludus kopi - Elev Popup");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

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

        /*overskrift.setBounds(100, 10, 150, 20);
        overskrift.setText("Download");


        /*fag.setBounds(50, 30, 200, 20);
        fag.setText("Skriv faget som aflevering er i");
        fagField.setBounds(50, 50, 200, 25);
        fagField.setToolTipText("f.eks.: Matematik");
        */
        
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