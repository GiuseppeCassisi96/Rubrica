
package com.mycompany.progettoturing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveSystem 
{
    boolean Save(int index, String nome, String cognome,String indirizzo, String telefono, int eta, AddressBook aBook)
    {
        aBook.ModifyPearson(index, nome, cognome, indirizzo, telefono, eta);
        File fileRubrica = new File("Informazioni.txt");
        try 
        {
            PrintStream pStream = new PrintStream(fileRubrica);
            for(int i = 0; i < aBook.GetListOfPeople().size(); i++)
            {
                Persona currentPearson = aBook.GetListOfPeople().get(i);
                pStream.println(currentPearson.GetNome() + ";"+ currentPearson.GetCognome()+ ";"
                + currentPearson.GetIndirizzo()+ ";" + currentPearson.GetTelefono()+ 
                ";"+ currentPearson.GetEta());
            }
            pStream.close();
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(SaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        return true;
    }
    
    void Load(AddressBook aBook)
    {
        File fileRubrica = new File("Informazioni.txt");
        try 
        {
            Scanner scanner = new Scanner(fileRubrica);
            Vector<String> values;
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                Persona p = new Persona(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]));
                aBook.AddNewPearson(p);
            }
            
        } catch (FileNotFoundException ex) 
        {
            Logger.getLogger(SaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
