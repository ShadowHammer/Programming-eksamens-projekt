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
 * @auther William
 */
public class Elev extends JFrame{
    private JPanel panelAfsluttet, panelAktive;
    private JButton aflever,download;
    private JTabbedPane afleveringer;
    public JTable afleveringerAfleveret,afleveringerIkkeAfleveret;
    private DatabaseHandler DB = new DatabaseHandler();
    
    public Elev(){
        
    }

    public void creatComponents(int x, int y) {
        setTitle("Ludus kopi - Elev");
        setSize(x,y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
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
        add(afleveringer);
        aflever = new JButton();
        download = new JButton();
        
        
        panelAktive = new JPanel();
        panelAfsluttet = new JPanel();
        
        afleveringer.addTab("", panelAktive);
        afleveringer.addTab("", panelAfsluttet);
        
        afleveringerIkkeAfleveret = new JTable(ting);
        afleveringerAfleveret = new JTable(ting);
        panelAktive.add(afleveringerIkkeAfleveret);
        panelAfsluttet.add(afleveringerAfleveret);
        panelAktive.add(aflever);
        panelAktive.add(download);
        
        panelAktive.setLayout(null);
        panelAfsluttet.setLayout(null);
        
        afleveringerIkkeAfleveret.setSize(600, 400);
        afleveringerIkkeAfleveret.setAutoResizeMode(5);
        
        afleveringerAfleveret.setSize(600, 400);
        afleveringerAfleveret.setAutoResizeMode(5);
        
        aflever.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ElevPopup popup = new ElevPopup();
                popup.koer(afleveringerIkkeAfleveret.getSelectedRow());
            }
        });
        
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
        
        aflever.setBounds(x/2, y-200, 150, 100);
        aflever.setText("Aflever");
        
        download.setBounds(x/2-300, y-200, 150, 100);
        download.setText("Hent baskrivelse");
        
        download.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DownloadElevPopup down = new DownloadElevPopup();
                down.koer(afleveringerIkkeAfleveret.getSelectedRow());
            }
        });
        
        
        
    }
    
}
