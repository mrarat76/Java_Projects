/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp2labproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monster
 */
public class Data {
    public static void main(String[] args) {
          
        Connection c=null;
    
        
        try {
            
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL ";
            String username = "root"; // Kullanıcı adını buraya girin
            String password = "arat";
            
           c = DriverManager.getConnection(url,username,password);
            System.out.println("Bağlantı tamam");
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
