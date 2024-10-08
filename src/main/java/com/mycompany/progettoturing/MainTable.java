
package com.mycompany.progettoturing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class MainTable extends JFrame 
{
    String[] colonne = {"Nome", "Cognome", "Telefono"};
    String[][] dati = {};
    JTable table;
    DefaultTableModel model;
    JFrame frame;
    JFrame loginFrame;
    Addressbook aBook;
    
    MainTable(Addressbook aBook)
    {
        this.aBook = aBook;
    }
    
    //Creates the login UI
    void LoginUI(SaveSystem save, Logger logger)
    {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(600, 600);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null); 

       
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());  
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  

        
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);  

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);  

        
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1;  
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(userField, gbc);

        gbc.gridx = 0;  
        gbc.gridy = 1;  
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(passLabel, gbc);

        gbc.gridx = 1;  
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(passField, gbc);

        
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Registrati");

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));  
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

       
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(buttonPanel, gbc);

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int code = logger.Login(userField, passField);
                if(code != -1)
                {
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                    InitUI();
                    save.setFileName(userField.getText()+code);
                    save.Load(aBook, model);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Utente non trovato!");
                }
                
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                 boolean result = logger.Register(userField, passField);
                 if(result)
                    JOptionPane.showMessageDialog(frame, "Registrazione completata!");
                 else
                    JOptionPane.showMessageDialog(frame, "Username o password già usati");
            }
        });

        
        loginFrame.add(loginPanel);
        
        
        loginFrame.setVisible(true);
    }
    
    
    //Creates the rubrica UI
    void InitUI()
    {
        frame = new JFrame("Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        model = new DefaultTableModel(dati, colonne){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.LINE_START);
        
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        JButton button1 = new JButton("Nuovo");
        JButton button2 = new JButton("Modifica");
        JButton button3 = new JButton("Elimina");
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //JDialog creation
                JDialog dialog = new JDialog(frame, "Editor persona", true);  
                dialog.setSize(800, 600);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLayout(new BorderLayout());
                
                //Text field creation
                JTextField nameText = new JTextField(15); 
                JTextField cognomeText = new JTextField(15); 
                JTextField indirizzoText = new JTextField(15); 
                JTextField telefonoText = new JTextField(15); 
                JTextField etaText = new JTextField(15); 
                
                //I create a JPanel to contains the labels and text fields
                JPanel inputPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(20, 50, 20, 50); 
                
                JToolBar toolBar = new JToolBar();
                toolBar.setFloatable(false);
                
                //Labels creation
                JLabel labelName = new JLabel("Nome:", SwingConstants.CENTER);
                JLabel labelCognome = new JLabel("Cognome:", SwingConstants.CENTER);
                JLabel labelIndirizzo = new JLabel("Indirizzo:", SwingConstants.CENTER);
                JLabel labelTelefono = new JLabel("Telefono:", SwingConstants.CENTER);
                JLabel labelEta = new JLabel("Eta:", SwingConstants.CENTER);
                
                JButton saveButton = new JButton("Salva");
                saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(nameText.getText().length() <= 0 || cognomeText.getText().length() <= 0 ||
                            indirizzoText.getText().length() <= 0 || telefonoText.getText().length() <= 0 ||
                            etaText.getText().length() <= 0)
                            {
                                JOptionPane.showMessageDialog(frame, "Compila tutti i campi per salvare!");
                                return;
                            }
                            if(etaText.getText().matches(".*[a-zA-Z]+.*") || 
                                    telefonoText.getText().matches(".*[a-zA-Z]+.*") )
                            {
                                JOptionPane.showMessageDialog(frame, "Il telefono e l'eta' devono contenere dei numeri "
                                        + ",non lettere! ");
                                return;
                            }
                            int eta = Integer.parseInt(etaText.getText());
                            Persona pearson = new Persona (nameText.getText(), cognomeText.getText(), 
                            indirizzoText.getText(), telefonoText.getText(), eta);
                            model.addRow(new Object[]{nameText.getText(),cognomeText.getText(),telefonoText.getText()});
                            aBook.AddNewPearson(pearson); 
                            dialog.dispose();
                        }
                    });
                JButton cancelButton = new JButton("Annulla");
                cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.dispose(); 
                        }
                    });
                
                toolBar.add(cancelButton);
                toolBar.add(saveButton);
                
                //Here I place the labels and text fields within the panel
                gbc.gridx = 0;  
                gbc.gridy = 0;  
                gbc.anchor = GridBagConstraints.LINE_END;
                inputPanel.add(labelName, gbc);
                
                gbc.gridx = 1;  
                gbc.anchor = GridBagConstraints.LINE_START;
                inputPanel.add(nameText, gbc);
                
                gbc.gridx = 0;  
                gbc.gridy = 1;  
                gbc.anchor = GridBagConstraints.LINE_END;
                inputPanel.add(labelCognome, gbc);
                
                gbc.gridx = 1;  
                gbc.anchor = GridBagConstraints.LINE_START;
                inputPanel.add(cognomeText, gbc);
                
                gbc.gridx = 0;  
                gbc.gridy = 2;  
                gbc.anchor = GridBagConstraints.LINE_END;
                inputPanel.add(labelIndirizzo, gbc);
                
                gbc.gridx = 1;  
                gbc.anchor = GridBagConstraints.LINE_START;
                inputPanel.add(indirizzoText, gbc);
                
                gbc.gridx = 0;  
                gbc.gridy = 3;  
                gbc.anchor = GridBagConstraints.LINE_END;
                inputPanel.add(labelTelefono, gbc);
                
                gbc.gridx = 1; 
                gbc.anchor = GridBagConstraints.LINE_START;
                inputPanel.add(telefonoText, gbc);
                
                gbc.gridx = 0;  
                gbc.gridy = 4; 
                gbc.anchor = GridBagConstraints.LINE_END;
                inputPanel.add(labelEta, gbc);
                
                gbc.gridx = 1;  
                gbc.anchor = GridBagConstraints.LINE_START;
                inputPanel.add(etaText, gbc);
                
                dialog.add(inputPanel, BorderLayout.CENTER);
                dialog.add(toolBar, BorderLayout.NORTH);
                

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
                    //JDialog creation
                    JDialog dialog = new JDialog(frame, "Editor persona", true);  
                    dialog.setSize(800, 600);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLayout(new BorderLayout());

                    Persona temp = aBook.GetListOfPeople().get(table.getSelectedRow());
                    //Text field creation
                    JTextField nameText = new JTextField(15);
                    nameText.setText(temp.GetNome());
                    JTextField cognomeText = new JTextField(15);
                    cognomeText.setText(temp.GetCognome());
                    JTextField indirizzoText = new JTextField(15);
                    indirizzoText.setText(temp.GetIndirizzo());
                    JTextField telefonoText = new JTextField(15); 
                    telefonoText.setText(temp.GetTelefono());
                    JTextField etaText = new JTextField(15);
                    etaText.setText(Integer.toString(temp.GetEta()));

                    //I create a JPanel to contains the labels and text fields
                    JPanel inputPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(20, 50, 20, 50); 

                    JToolBar toolBar = new JToolBar();
                    toolBar.setFloatable(false);

                    //Labels creation
                    JLabel labelName = new JLabel("Nome:", SwingConstants.CENTER);
                    JLabel labelCognome = new JLabel("Cognome:", SwingConstants.CENTER);
                    JLabel labelIndirizzo = new JLabel("Indirizzo:", SwingConstants.CENTER);
                    JLabel labelTelefono = new JLabel("Telefono:", SwingConstants.CENTER);
                    JLabel labelEta = new JLabel("Eta:", SwingConstants.CENTER);

                    JButton saveButton = new JButton("Salva");
                    saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(etaText.getText().matches(".*[a-zA-Z]+.*") || 
                                    telefonoText.getText().matches(".*[a-zA-Z]+.*") )
                            {
                                JOptionPane.showMessageDialog(frame, "Il telefono e l'eta' devono contenere dei numeri "
                                        + ",non lettere! ");
                                return;
                            }
                            int eta = Integer.parseInt(etaText.getText());
                            Persona pearson = new Persona (nameText.getText(), cognomeText.getText(), 
                            indirizzoText.getText(), telefonoText.getText(), eta);
                            aBook.ModifyPearson(table.getSelectedRow(), nameText.getText(), cognomeText.getText(),
                            indirizzoText.getText(), telefonoText.getText(), eta);
                            model.setValueAt(nameText.getText(), table.getSelectedRow(), 0);
                            model.setValueAt(cognomeText.getText(), table.getSelectedRow(), 1);
                            model.setValueAt(telefonoText.getText(), table.getSelectedRow(), 2);
                            dialog.dispose();
                        }
                    });
                    JButton cancelButton = new JButton("Annulla");
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.dispose();  
                        }
                    });

                    toolBar.add(cancelButton);
                    toolBar.add(saveButton);

                    //Here I place the labels and text fields within the panel
                    gbc.gridx = 0;  
                    gbc.gridy = 0;  
                    gbc.anchor = GridBagConstraints.LINE_END;
                    inputPanel.add(labelName, gbc);

                    gbc.gridx = 1;  
                    gbc.anchor = GridBagConstraints.LINE_START;
                    inputPanel.add(nameText, gbc);

                    gbc.gridx = 0;  
                    gbc.gridy = 1;  
                    gbc.anchor = GridBagConstraints.LINE_END;
                    inputPanel.add(labelCognome, gbc);

                    gbc.gridx = 1;  
                    gbc.anchor = GridBagConstraints.LINE_START;
                    inputPanel.add(cognomeText, gbc);

                    gbc.gridx = 0;  
                    gbc.gridy = 2;  
                    gbc.anchor = GridBagConstraints.LINE_END;
                    inputPanel.add(labelIndirizzo, gbc);

                    gbc.gridx = 1;  
                    gbc.anchor = GridBagConstraints.LINE_START;
                    inputPanel.add(indirizzoText, gbc);

                    gbc.gridx = 0;  
                    gbc.gridy = 3;  
                    gbc.anchor = GridBagConstraints.LINE_END;
                    inputPanel.add(labelTelefono, gbc);

                    gbc.gridx = 1; 
                    gbc.anchor = GridBagConstraints.LINE_START;
                    inputPanel.add(telefonoText, gbc);

                    gbc.gridx = 0;  
                    gbc.gridy = 4; 
                    gbc.anchor = GridBagConstraints.LINE_END;
                    inputPanel.add(labelEta, gbc);

                    gbc.gridx = 1;  
                    gbc.anchor = GridBagConstraints.LINE_START;
                    inputPanel.add(etaText, gbc);

                    dialog.add(inputPanel, BorderLayout.CENTER);
                    dialog.add(toolBar, BorderLayout.NORTH);


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
        
        
        
        toolBar.add(button1);
        toolBar.add(button2);
        toolBar.add(button3);
        panel.add(toolBar, BorderLayout.NORTH);
        
        frame.add(panel);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
   
    

}
