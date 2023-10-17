/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp2labproject;

/**
 *
 * @author Monster
 */
public class Personnel extends PersonnelPage {

    private String name;
    private String surname;
    private String password;
    private int Salary;
    private int Advance;
    private int ID;

    /**
     *
     * @return
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
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the Salary
     */
    public int getSalary() {
        return Salary;
    }

    /**
     * @param Salary the Salary to set
     */
    public void setSalary(int Salary) {
        this.Salary = Salary;
    }

    /**
     * @return the Advance
     */
    public int getAdvance() {
        return Advance;
    }

    /**
     * @param Advance the Advance to set
     */
    public void setAdvance(int Advance) {
        this.Advance = Advance;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

}
