/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.endoftermproject;

import java.util.ArrayList;

/**
 *
 * @author Monster
 */
public class Users {

    protected String name;
    protected String surname;
    protected String password;
    protected String username;
    static ArrayList<Patients> PatientArray = new ArrayList<>();
    static ArrayList<LabMan> LabManArray = new ArrayList<>();
    static ArrayList<Admin> AdminArray = new ArrayList<>();
    static Doctors[] DoctorArray = new Doctors[2];
    String labresult;
    int requested;

    public Users(String name, String surname, String password, String username) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void createLabMan() {

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

    public void createPatient() {
        for (int i = 0; i < PatientArray.size(); i++) {
            if (PatientArray.get(i) == null) {

                Patients patient1 = new Patients(name, surname, password, username, labresult, requested);
                patient1.setName(name);
                patient1.setPassword(password);
                patient1.setSurname(surname);
                patient1.setUsername(username);
                patient1.setLabresult(labresult);

                PatientArray.add(patient1);
            }

        }

    }

    public void removeLabman() {
        for (int i = 0; i < LabManArray.size(); i++) {
            if (LabManArray.get(i) != null) {
                LabManArray.remove(i);
            }
        }

    }

    public void removeLabman(int index) {

        if (LabManArray.get(index) != null) {
            LabManArray.remove(index);

        }

    }

    public void removePatient() {
        for (int i = 0; i < LabManArray.size(); i++) {
            if (LabManArray.get(i) != null) {
                LabManArray.remove(i);
            }

        }
    }

    public void removePatient(int index) {

        if (LabManArray.get(index) != null) {
            LabManArray.remove(index);

        }
    }

    public void displayLabman() {
        for (int i = 0; i < LabManArray.size(); i++) {
            System.out.println("The LabMan's name is"+ LabManArray.get(i).getName());
            System.out.println("The LabMan's surname is"+ LabManArray.get(i).getSurname() );
            System.out.println("The LabMan's username is"+ LabManArray.get(i).getUsername() );
            System.out.println("The LabMan's password is"+ LabManArray.get(i).getPassword()); 

        }
    }

    public void displayLabman(int index) {

            System.out.println("The LabMan's name is"+ LabManArray.get(index).getName());
            System.out.println("The LabMan's surname is"+ LabManArray.get(index).getSurname() );
            System.out.println("The LabMan's username is"+ LabManArray.get(index).getUsername() );
            System.out.println("The LabMan's password is"+ LabManArray.get(index).getPassword()); 
            
    }

    public void displayPatients() {
        for (int i = 0; i < PatientArray.size(); i++) {
            System.out.println("The patient's name is"+ PatientArray.get(i).getName()); 
            System.out.println("The patient's surname is"+ PatientArray.get(i).getSurname());
            System.out.println("The patient's username is"+ PatientArray.get(i).getUsername()); 
            System.out.println("The patient's password is"+ PatientArray.get(i).getPassword());
        }
    }

    public void displayPatients(int index) {
            System.out.println("The patient's name is"+ PatientArray.get(index).getName()); 
            System.out.println("The patient's surname is"+ PatientArray.get(index).getSurname());
            System.out.println("The patient's username is"+ PatientArray.get(index).getUsername()); 
            System.out.println("The patient's password is"+ PatientArray.get(index).getPassword());
           
    }

    public void createDoctor(String name, String surname, String password, String username) {
        for (int i = 0; i < DoctorArray.length; i++) {
            if (DoctorArray[i] == null) {
                Doctors doctor1 = new Doctors(name, surname, password, username);
                doctor1.setName(name);
                doctor1.setPassword(password);
                doctor1.setSurname(surname);
                doctor1.setUsername(username);

                DoctorArray[i] = doctor1;

            }
        }

    }

    public void removeDoctor() {
        for (int i = 0; i < DoctorArray.length; i++) {
            if (DoctorArray[i] != null) {
                DoctorArray[i] = null;

            }
        }
    }

    public int removeDoctor(int index) {
        if (DoctorArray[index] != null) {
            DoctorArray[index] = null;

        }
        return index;
    }

    public void displayDoctor() {
        for (Doctors doc : DoctorArray) {
            System.out.println("Username is:" + doc.username);
            System.out.println("Password is:" + doc.password);
            System.out.println("Doctor's name is:" + doc.name);
            System.out.println("Doctor's surname is:" + doc.surname);
        }

    }

    public void displayDoctor(int index) {
        System.out.println(DoctorArray[index]);
    }

    public void requestLabTest(String name) {
        for (Patients patient : PatientArray) {
            if (patient.getName().equals(name)) {
                patient.requested = +1;

            }
        }
    }

    public void setLabReport(String username, String labpresult) {

        for (Patients patient : PatientArray) {
            if (patient.getUsername().equals(username)) {
                patient.setLabresult(labpresult);
                patient.requested = -1;
            }
        }

//u
    }

    public static Users VerifyAccountMethod(String username, String password) {
        Users user = null;
        for (LabMan LabMancheck : LabManArray) {
            if (LabMancheck.getUsername().equalsIgnoreCase(username) && LabMancheck.getPassword().equalsIgnoreCase(password)) {
                user = LabMancheck;
                return user;
            }
        }

        for (Doctors doctorarr : DoctorArray) {
            if (doctorarr == null) {
                continue;
            } else if (doctorarr.getUsername().equalsIgnoreCase(username) && doctorarr.getPassword().equalsIgnoreCase(password)) {
                user = doctorarr;

                return user;

            }
        }

        for (Patients patientscheck : PatientArray) {
            if (patientscheck.getUsername().equalsIgnoreCase(username) && patientscheck.getPassword().equalsIgnoreCase(password)) {
                user = patientscheck;
                return user;
            }
        }
        for (Admin admincheck : AdminArray) {
            if (admincheck.getUsername().equalsIgnoreCase(username) && admincheck.getPassword().equalsIgnoreCase(password)) {
                user = admincheck;
                return user;
            }
        }

        return user;
    }

    public void displayapatient(String username) {

        for (Patients patient : PatientArray) {
            if (patient.getUsername().equals(username)) {
                System.out.println(patient.getName());
                System.out.println(patient.getSurname());

                if (patient.getrequest() < 1) {
                    System.out.println(patient.getName() + "named patient's lab result is :" + patient.getLabresult());
                } else {
                    System.out.println("The lab result is not ready.");
                }

            }
        }
    }

    public void displayLabreportofapatient(String username) {
        for (Patients patient : PatientArray) {
            if (patient.getName().equals(name)) {
                System.out.println("The lab result of " + patient.getName() + patient.getSurname() + "is:" + patient.getLabresult());
            }

        }
    }

    public void removedoctorbyusername(String username) {
        for (int i = 0; i < DoctorArray.length; i++) {
            if (DoctorArray[i].getUsername().equals(username)) {
                DoctorArray[i] = null;
                break;

            }
        }
    }

    public void removeLabManbyusername(String username) {
        for (LabMan labman : LabManArray) {
            if (labman.getUsername().equals(username)) {
                LabManArray.remove(labman);
                break;
            }
        }
    }

}
