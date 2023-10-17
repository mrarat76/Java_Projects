/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.endoftermproject;

/**
 *
 * @author Monster
 */
public class Patients extends Users {

    private String labresult = "";

    public Patients(String name, String surname, String password, String username, String labresult, int requested) {
        super(name, surname, password, username);
        this.labresult = labresult;
        this.requested=requested;
    }

    int usernamecount = 1;

    @Override
    public void setUsername(String username) {
        String add = "Pat_";
        this.username = add + usernamecount + username;
        usernamecount++;
    }

    @Override
    public void setPassword(String password) {

        String add = "Pat_";

        this.password = add + password;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getLabresult() {
        return labresult;
    }

    public void setLabresult(String labresult) {
        this.labresult = labresult;
    }

    public void setrequest(int request){
        this.requested=request;
    }
    public int getrequest(){
        return requested;
    }
    
    
  
}
