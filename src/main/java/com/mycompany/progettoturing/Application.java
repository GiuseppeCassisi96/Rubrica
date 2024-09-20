package com.mycompany.progettoturing;

import javax.swing.SwingUtilities;


public class Application 
{

    public static void main(String[] args)
    {
        System.out.println("Application start");
        SaveSystem save = new SaveSystem();
        AddressBook adBook = new AddressBook(save); 
        MainTable myUI = new MainTable(adBook);
        myUI.InitUI();  
        save.Load(adBook, myUI.model);
        
    }
}
