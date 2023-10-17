/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.endoftermproject;

/**
 *
 * @author Monster
 */
public class LogIn {
    
    public static void LogInCheck(String username, String password) {
        Users user = Users.VerifyAccountMethod(username, password);

        if (user != null) {
            if (user instanceof LabMan) {
                EndOfTermProject.accountType = EndOfTermProject.AccountType.LABMAN;
                EndOfTermProject.SetUserAccount(user);
            } else if (user.getClass().equals(Doctors.class)) {
                EndOfTermProject.accountType = EndOfTermProject.AccountType.DOCTOR;
                EndOfTermProject.SetUserAccount(user);
            } else if (user.getClass().equals(Patients.class)) {
                EndOfTermProject.accountType = EndOfTermProject.AccountType.PATIENT;
                EndOfTermProject.SetUserAccount(user);
            } else if (user instanceof Admin){
             EndOfTermProject.accountType= EndOfTermProject.AccountType.ADMIN;
             EndOfTermProject.SetUserAccount(user);
        }
            
        }
    }
    
    
    
    
}
