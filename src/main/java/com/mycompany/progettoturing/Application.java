package com.mycompany.progettoturing;

import javax.swing.SwingUtilities;


public class Application 
{

    public static void main(String[] args)
    {
        System.out.println("Application start");
        SaveSystem save = new SaveSystem();
        Logger logger = new Logger();
        AddressBook adBook = new AddressBook(save); 
        MainTable myUI = new MainTable(adBook);
        myUI.LoginUI(save, logger);
        
    }
}
