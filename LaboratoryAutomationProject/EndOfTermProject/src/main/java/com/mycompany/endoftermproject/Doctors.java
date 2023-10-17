/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.endoftermproject;

/**
 *
 * @author Monster
 */
public class Doctors extends Users {

    public Doctors(String name, String surname, String password, String username) {
        super(name, surname, password, username);
        
    }

    int usernamecount = 1;

    @Override
    public void setUsername(String username) {
        String add = "Doc_";
        this.username = add + usernamecount + username;
        usernamecount++;
    }

    @Override
    public void setPassword(String password) {

        String add = "Doc_";

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

    /**
     *
     * @param name
     * @param surname
     * @param password
     * @param username
     */
    public void createPatient(String name, String surname, String password, String username) {
        for (int i = 0; i < PatientArray.size(); i++) {
            if (PatientArray.get(i) == null) {

                Patients patient1 = new Patients(name, surname, password, username, labresult,requested);
                patient1.setName(name);
                patient1.setPassword(password);
                patient1.setSurname(surname);
                patient1.setUsername(username);
                patient1.setLabresult(labresult);

                PatientArray.add(patient1);
            }

        }
        
        
        

    }
    
    
    @Override
      public void removePatient() {
        for (int i = 0; i < LabManArray.size(); i++) {
            if (LabManArray.get(i) != null) {
                LabManArray.remove(i);
            }

        }
    }
    
    
    
    @Override
     public void removePatient(int index) {
        
            if (LabManArray.get(index) != null) {
                LabManArray.remove(index);
            

        }
    }
    
     
    @Override
    public void displayPatients() {
        for (int i = 0; i < PatientArray.size(); i++) {
            PatientArray.get(i);
        }
    }
    
    
    @Override
     public void displayPatients(int index) {
       
            PatientArray.get(index);
        
    }
     
     
    
    
}
