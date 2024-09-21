/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoturing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JTextField;

/**
 *
 * @author Giuse
 */
public class Logger 
{
    private Vector<Utente> users;
    
    Logger()
    {
        users = new Vector<Utente>();
        File fileRubrica = new File("Users.txt");
        if(!fileRubrica.exists())
        {
            try 
            {
                fileRubrica.createNewFile();
                return;
            } catch (IOException ex) 
            {
                java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try 
        {
            Scanner scanner = new Scanner(fileRubrica);
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                Utente u = new Utente(data[0], data[1], Integer.parseInt(data[2]));
                users.add(u);
            }
        } catch (FileNotFoundException ex) 
        {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Logs in a specific user
    public int Login(JTextField usernameTField, JTextField passwordTField)
    {
        File fileRubrica = new File("Users.txt");
        try 
        {
            Scanner scanner = new Scanner(fileRubrica);
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                if(data[0].equals(usernameTField.getText()) && data[1].equals(passwordTField.getText()))
                {
                    //User found 
                    return Integer.parseInt(data[2]);
                }
            }
        } catch (FileNotFoundException ex) 
        {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            //User not found
            return -1;
        }
        //User not found
        return -1;
    }
    
    //Create a new user
    public boolean Register(JTextField usernameTField, JTextField passwordTField)
    {
        Random rand = new Random();
        Utente newUser = new Utente(usernameTField.getText(), 
        passwordTField.getText(), rand.nextInt(10000000));
        if(users.size() > 0)
        {
            for(int i = 0; i < users.size(); i++)
            {
                Utente currentUser = users.get(i);
                if(currentUser.getUsername().equals(usernameTField.getText()) || 
                currentUser.getPassword().equals(passwordTField.getText()))
                {
                    return false;
                }
            }
        }
        users.add(newUser);
        
        //Save the new user in a file
        File fileRubrica = new File("Users.txt");
        try 
        {
            PrintStream pStream = new PrintStream(fileRubrica);
            for(int i = 0; i < users.size(); i++)
            {
                Utente currentUser = users.get(i);
                pStream.println(currentUser.getUsername() + ";"+ currentUser.getPassword()
                        + ";"+currentUser.getUserID());
            }
            pStream.close();
        } 
        catch (FileNotFoundException ex) 
        {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
