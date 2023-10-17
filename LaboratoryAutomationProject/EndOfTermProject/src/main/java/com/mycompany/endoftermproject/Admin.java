/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.endoftermproject;

/**
 *
 * @author Monster
 */
public class Admin extends Users {
    
    public Admin(String name, String surname, String password, String username) {
        super(name, surname, password, username);
       
    }
    
    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    @Override
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    
   
    public void createLabMan(String name, String surname, String password, String username) {

        for (int i = 0; i < LabManArray.size(); i++) {
            if (LabManArray.get(i) == null) {

                LabMan labman1 = new LabMan(name, surname, password, username);
                labman1.setName(name);
                labman1.setPassword(password);
                labman1.setSurname(surname);
                labman1.setUsername(username);
             
                LabManArray.add(labman1);

            }
        }

    }
    
}
