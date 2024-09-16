package com.mycompany.progettoturing;


public class Application 
{

    public static void main(String[] args) 
    {
        AddressBook adBook = new AddressBook();
        SaveSystem save = new SaveSystem();
        save.Load(adBook);
        save.Save(1, "Tommaso", "Paradiso", "Via cielo", "090120230", 30, adBook);
        System.out.println("Application start");
    }
}
