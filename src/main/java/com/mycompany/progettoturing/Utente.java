/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoturing;


/**
 *
 * @author Giuse
 */
public class Utente 
{
    private String username;
    private String password;
    private String fileName;
    private int userID;
    
    Utente(String username, String password, int userID)
    {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.fileName = username + userID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }
}
