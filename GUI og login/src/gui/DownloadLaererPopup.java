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
    private JLabel guide,overskrift,fil;
    private JTextField filField;
    private JPanel popupPanel;
    private JButton download;
    private DatabaseHandler DB = new DatabaseHandler();

    public DownloadLaererPopup(){

}
    
    public void koer(int selectedRow){
        
    
        setTitle("Ludus kopi - Lærer Popup");
        setSize(300,205);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        guide = new JLabel();
        fil = new JLabel();
        filField = new JTextField();
        overskrift = new JLabel();
        

        download = new JButton();

        popupPanel = new JPanel();
        add(popupPanel);
        popupPanel.setLayout(null);

        
        popupPanel.add(filField);
        popupPanel.add(fil);
        
        popupPanel.add(download);
        popupPanel.add(overskrift);

        overskrift.setBounds(100, 10, 150, 20);
        overskrift.setText("Hent besvarelse");

        
        fil.setBounds(50, 30, 200, 20);
        fil.setText("Skriv stien til download sted");
        filField.setBounds(50, 50, 200, 25);
        filField.setToolTipText("Skriv hele stien til den mappe filen skal ligge i");

        download.setBounds(110,80,100,30);
        download.setText("Download");

        
        

        download.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    String filen = filField.getText();
                    
                    DB.downloadFile(selectedRow, filen, false);
                    
                    System.out.println(filen);

                }
            });
    }}