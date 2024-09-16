
package com.mycompany.progettoturing;
import java.util.Vector;

public class Addressbook 
{
    private Vector<Persona> listOfPeople;
    boolean AddNewPearson(Persona pearson)
    {
       return listOfPeople.add(pearson);
    }
    
    void ModifyPearson(int index, String nome, String cognome,String indirizzo, String telefono, int eta)
    {
        listOfPeople.get(index).SetNome(nome);
        listOfPeople.get(index).SetCognome(cognome);
        listOfPeople.get(index).SetIndirizzo(indirizzo);
        listOfPeople.get(index).SetTelefono(telefono);
        listOfPeople.get(index).SetEta(eta);
        
    }
}
