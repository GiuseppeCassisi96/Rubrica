
package com.mycompany.progettoturing;
import java.util.Vector;

public class Addressbook 
{
    private Vector<Persona> listOfPeople;
    SaveSystem save;
    
    Addressbook(SaveSystem save)
    {
        listOfPeople = new Vector<Persona>();
        this.save = save;
    }
    boolean AddNewPearson(Persona pearson)
    {
       GetListOfPeople().add(pearson);
       save.Save(this);
       return true;
    }
    
    void ModifyPearson(int index, String nome, String cognome,String indirizzo, String telefono, int eta)
    {
        GetListOfPeople().get(index).SetNome(nome);
        GetListOfPeople().get(index).SetCognome(cognome);
        GetListOfPeople().get(index).SetIndirizzo(indirizzo);
        GetListOfPeople().get(index).SetTelefono(telefono);
        GetListOfPeople().get(index).SetEta(eta);
        save.Save(this);
        
    }
    
    void RemovePearson(int index)
    {
        GetListOfPeople().remove(index);
        save.Save(this);
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
