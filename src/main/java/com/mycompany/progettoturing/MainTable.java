
package com.mycompany.progettoturing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class MainTable extends JFrame 
{
    String[] colonne = {"Nome", "Cognome", "Telefono"};
    String[][] dati = {};
    JTable table;
    DefaultTableModel model;
    JFrame frame;
    AddressBook aBook;
    
    MainTable(AddressBook aBook)
    {
        this.aBook = aBook;
    }
    void InitUI()
    {
        frame = new JFrame("Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        model = new DefaultTableModel(dati, colonne);
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.LINE_START);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton button1 = new JButton("Nuovo");
        JButton button2 = new JButton("Modifica");
        JButton button3 = new JButton("Elimina");
        
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JDialog dialog = new JDialog(frame, "Dialogo Modale", true);  
                dialog.setSize(800, 600);

                JLabel label = new JLabel("Editor persona", SwingConstants.CENTER);
                dialog.add(label);

                dialog.setVisible(true);
            }
        });
        
        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(table.getSelectedRowCount() == 0)
                {
                    JOptionPane.showMessageDialog(frame, "Bisogna selezionare una persona prima di modificarla!");
                }
                else
                {
                    JDialog dialog = new JDialog(frame, "Dialogo Modale", true);  
                    dialog.setSize(800, 600);

                    JLabel label = new JLabel("Editor persona", SwingConstants.CENTER);
                
                    dialog.add(label);

                    dialog.setVisible(true);
                }
                
            }
        });
        
        button3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(table.getSelectedRowCount() == 0)
                {
                    JOptionPane.showMessageDialog(frame, "Bisogna selezionare una persona prima di eliminarla!");
                }
                else
                {
                    String name = (String) table.getValueAt(table.getSelectedRow(), 0);
                    String surname = (String) table.getValueAt(table.getSelectedRow(), 1);
                    int option = JOptionPane.showConfirmDialog(frame, "Eliminare la persona " 
                    + name + " " + surname + "?");
                    if(option == JOptionPane.YES_OPTION)
                    {
                        aBook.RemovePearson(table.getSelectedRow());
                        model.removeRow(table.getSelectedRow());
                        
                    }
                        
                
                }
                
            }
        });
        
        
        
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.add(panel);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
   
    

}
