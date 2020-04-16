/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Tobias
 */
public class Elev {
    private JPanel panelAfsluttet, panelAktive;
    private JLabel label1;
    private JButton knap1;
    private JButton knap2;
    private JTabbedPane afleveringer;

    public Elev(int x, int y){
        creatComponents(x,y);
    }

    private void creatComponents(int x, int y) {
        afleveringer = new JTabbedPane();
        add(afleveringer);
        
        panelAktive = new JPanel();
        panelAfsluttet = new JPanel();
        
        afleveringer.addTab("", panelAktive);
        afleveringer.addTab("", panelAfsluttet);
        
        
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

    private void add(JTabbedPane afleveringer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
