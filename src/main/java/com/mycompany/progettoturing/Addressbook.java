
package com.mycompany.progettoturing;
import java.util.Vector;

public class AddressBook 
{
    private Vector<Persona> listOfPeople;
    
    AddressBook()
    {
        listOfPeople = new Vector<Persona>();
    }
    boolean AddNewPearson(Persona pearson)
    {
       return GetListOfPeople().add(pearson);
    }
    
    void ModifyPearson(int index, String nome, String cognome,String indirizzo, String telefono, int eta)
    {
        GetListOfPeople().get(index).SetNome(nome);
        GetListOfPeople().get(index).SetCognome(cognome);
        GetListOfPeople().get(index).SetIndirizzo(indirizzo);
        GetListOfPeople().get(index).SetTelefono(telefono);
        GetListOfPeople().get(index).SetEta(eta);
        
    }
    
    void RemovePearson(int index)
    {
        GetListOfPeople().remove(index);
    }

    /**
     * @return the listOfPeople
     */
    public Vector<Persona> GetListOfPeople() {
        return listOfPeople;
    }

    /**
     * @param listOfPeople the listOfPeople to set
     */
    public void SetListOfPeople(Vector<Persona> listOfPeople) {
        this.listOfPeople = listOfPeople;
    }
}
