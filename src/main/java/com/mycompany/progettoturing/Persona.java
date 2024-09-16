
package com.mycompany.progettoturing;

public class Persona 
{


    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private int eta;
    
    Persona(String nome, String cognome,String indirizzo, String telefono, int eta)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.eta = eta;
    }
    
    
        /**
     * @return the nome
     */
    public String GetNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void SetNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cognome
     */
    public String GetCognome() {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void SetCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return the indirizzo
     */
    public String GetIndirizzo() {
        return indirizzo;
    }

    /**
     * @param indirizzo the indirizzo to set
     */
    public void SetIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * @return the telefono
     */
    public String GetTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void SetTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the eta
     */
    public int GetEta() {
        return eta;
    }

    /**
     * @param eta the eta to set
     */
    public void SetEta(int eta) {
        this.eta = eta;
    }
    
    
}
