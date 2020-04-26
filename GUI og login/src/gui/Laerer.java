/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Tobias
 */
public class Laerer extends JFrame {
    private JPanel panelAfsluttet, panelAktive;
    private JLabel label1;
    private JButton knap1;
    private JButton knap2;
    private JTable afleveringerIkkeAfleveret, afleveringerAfleveret;
    private JTabbedPane afleveringer;
    private JScrollPane scrPane1,scrPane2;

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
          "First Name",
          "Date",
          "Teacher",
          "Assignment",
          "Answer"
        }; 
        
        DefaultTableModel ting = new DefaultTableModel(columnNames,0);
        ArrayList<Object[]> temp = new ArrayList<>(Arrays.asList(data));
        
        for (int i = 0; i < temp.size(); i++){
            Object[] row = temp.get(i);
            ting.addRow(row);
        }
        
   
        afleveringer = new JTabbedPane();
        panelAktive = new JPanel();
        panelAktive.setLayout(null);
        panelAfsluttet = new JPanel();
        
        afleveringerIkkeAfleveret = new JTable(ting);
        afleveringerAfleveret = new JTable(ting);
        knap1 = new JButton("Tilføj aflevering");
        knap2 = new JButton();
        scrPane1 = new JScrollPane();
        scrPane2 = new JScrollPane();
        
        afleveringer.addTab("", panelAktive);
        afleveringer.addTab("", panelAfsluttet);
        panelAktive.add(afleveringerIkkeAfleveret);
        panelAfsluttet.add(afleveringerAfleveret);
        afleveringerIkkeAfleveret.setSize(600, 400);
        afleveringerIkkeAfleveret.setAutoResizeMode(5);
        panelAktive.add(knap1);
        panelAktive.add(scrPane1);
        panelAfsluttet.add(scrPane2);
        
        
        knap1.setBounds(x/2, y-200, 100, 100);
        
        
        
        
        
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
        
        /*combo box
        /JTable (til at vise database)                          (tjek)
        /2 lærer og 2 elever 
        /popup box til indput til databasen med kalender
        /
        */
        
        add(afleveringer);
    }
}
    
