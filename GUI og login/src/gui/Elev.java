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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tobias
 */
public class Elev extends JFrame{
    private JPanel panelAfsluttet, panelAktive;
    private JLabel label1;
    private JButton knap1;
    private JButton knap2;
    private JTabbedPane afleveringer;
    private JTable afleveringerAfleveret,afleveringerIkkeAfleveret;

    public Elev(){
        
    }

    public void creatComponents(int x, int y) {
        setTitle("Ludus kopi - Elev");
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
        add(afleveringer);
        
        
        
        panelAktive = new JPanel();
        panelAfsluttet = new JPanel();
        
        afleveringer.addTab("", panelAktive);
        afleveringer.addTab("", panelAfsluttet);
        
        afleveringerIkkeAfleveret = new JTable(ting);
        afleveringerAfleveret = new JTable(ting);
        panelAktive.add(afleveringerIkkeAfleveret);
        panelAfsluttet.add(afleveringerAfleveret);
        
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
        /button
        /JTable (til at vise database)
        /2 lærer og 2 elever
        /popup box til indput til databasen
        /
        */
        
        
    }
}
