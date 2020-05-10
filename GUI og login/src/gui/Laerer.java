/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Tobias
 * @author William
 */
public class Laerer extends JFrame {
    private JPanel panelAfsluttet, panelAktive;
    private JButton opretAflevering, download;
    private JTable afleveringerIkkeAfleveret, afleveringerAfleveret;
    private JTabbedPane afleveringer;
    private DatabaseHandler DB = new DatabaseHandler();

    public Laerer(){
        
    }

    

    public void creatComponents(int x, int y) {
        
        setTitle("Ludus kopi - Lærer");
        setSize(x,y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        Object [][] data = {
            {"Matematik","02.01.2000","TP","Opgave",""},
            {"Matematik","02.01.2001","TP","Opgave",""},
            {"Matematik","02.01.2002","TP","Opgave",""},
            {"Matematik","02.01.2003","TP","Opgave",""},
            {"Matematik","02.01.2004","TP","Opgave",""},
            {"Matematik","02.01.2005","TP","Opgave",""},
            {"Matematik","02.01.2006","TP","Opgave",""},
            {"Matematik","02.01.2007","TP","Opgave",""},
            {"Matematik","02.01.2007","TP","Opgave",""}
        };


        
        String[] columnNames = {
            "Fag",
            "Dato",
            "Assignment",
            "Answer"
        }; 
        
        DefaultTableModel ting = new DefaultTableModel(columnNames,0) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        ArrayList<Object[]> temp = DB.getAssignmentTable();
        
        for (int i = 0; i < temp.size(); i++){
            Object[] row = temp.get(i);
            ting.addRow(row);
        }
        
   
        afleveringer = new JTabbedPane();
        panelAktive = new JPanel();
        panelAktive.setLayout(null);
        panelAfsluttet = new JPanel();
        panelAfsluttet.setLayout(null);
        
        afleveringerIkkeAfleveret = new JTable(ting);
        afleveringerAfleveret = new JTable(ting);
        
        
        
        opretAflevering = new JButton("Tilføj aflevering");
        download = new JButton("Download aflevering");
        opretAflevering.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                LaererPopup popup = new LaererPopup();
                popup.koer();
                
            }
        });
        
        
        
        download.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int curRow = afleveringerAfleveret.getSelectedRow();
                if (curRow == -1){
                    
                }else{
                    DownloadLaererPopup down = new DownloadLaererPopup();
                    down.koer(afleveringerAfleveret.getSelectedRow());
                }
                
            }
        });
        
        add(afleveringer);
        afleveringer.addTab("", panelAktive);
        afleveringer.addTab("", panelAfsluttet);
        panelAktive.add(afleveringerIkkeAfleveret);
        panelAfsluttet.add(afleveringerAfleveret);
        panelAfsluttet.add(download);
        afleveringerIkkeAfleveret.setSize(600, 400);
        afleveringerIkkeAfleveret.setAutoResizeMode(5);
        
        afleveringerAfleveret.setSize(600, 400);
        afleveringerAfleveret.setAutoResizeMode(5);
        panelAktive.add(opretAflevering);
        
        
        
        opretAflevering.setBounds(x/2, y-200, 150, 100);
        download.setBounds(x/2, y-200, 150, 100);
        
        
        
        
        
        /*Kode hjulpet med at opfylde det udsene vi ville have til vores program
        /https://stackoverflow.com/questions/9052784/set-size-of-tab-in-jtabbedpane
        /
        */
        int size = (x/2)-40; 
        JLabel tab1Title = new JLabel("Aktive afleveringer");
        tab1Title.setPreferredSize(new Dimension(size, 20));
        JLabel tab2Title = new JLabel("Afsluttede afleveringer");
        tab2Title.setPreferredSize(new Dimension((size)-40, 20));
        
        afleveringer.setTabComponentAt(0, tab1Title);
        afleveringer.setTabComponentAt(1, tab2Title);
        
        add(afleveringer);
    }
}
    
