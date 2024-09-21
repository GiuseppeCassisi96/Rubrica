
package com.mycompany.progettoturing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class SaveSystem 
{
    private String fileName;
    SaveSystem()
    {
        Path directoryPath = Paths.get("Informazioni");
        if (!Files.exists(directoryPath)) {
            try 
            {
                Files.createDirectories(directoryPath);
            } catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    //Save all informations in the user's file  
    boolean Save(int index, String nome, String cognome,String indirizzo, String telefono, int eta, Addressbook aBook)
    {
        aBook.ModifyPearson(index, nome, cognome, indirizzo, telefono, eta);
        File fileRubrica = new File("Informazioni/"+fileName+".txt");
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
    
    //Save all informations in the user's file  
    boolean Save(Addressbook aBook)
    {
        File fileRubrica = new File("Informazioni/"+fileName+".txt");
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
    
    
    //Load all informations of the user 
    void Load(Addressbook aBook, DefaultTableModel model)
    {
        File fileRubrica = new File("Informazioni/"+fileName+".txt");
        if(!fileRubrica.exists())
            return;
        try 
        {
            Scanner scanner = new Scanner(fileRubrica);
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                Persona p = new Persona(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]));
                Object[] newRow = {p.GetNome(), p.GetCognome(), p.GetTelefono()};
                model.addRow(newRow);
                aBook.AddNewPearson(p);
            }
            
        } catch (FileNotFoundException ex) 
        {
            Logger.getLogger(SaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
