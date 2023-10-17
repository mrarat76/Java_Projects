/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp2labproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Monster
 */
public class User extends UserLogin {

    /*public boolean kullaniciGiris(String kullaniciAdi, String sifre) {

// Kullanıcı adı ve şifre doğrulaması yapılabilir
        int rowCount = model.getRowCount(); //satır sayısını al
        for (int i = 0; i < rowCount; i++) { //her satırı döngüyle gezmeye başla
            int ID= (Integer) model.getValueAt(i, 0);
            String name = (String) model.getValueAt(i, 1); //i. satırdaki adı al
            String surname = (String) model.getValueAt(i, 2); //i. satırdaki soyadı al
            int salary = (Integer) model.getValueAt(i, 3);
            int advance = (Integer) model.getValueAt(i, 4);
            String status = (String) model.getValueAt(i, 5);
            String username = name.toLowerCase(); //kullanıcı adını oluştur
            String password = surname.toLowerCase();

            //Kullanıcı doğrulama işlemini yap
            //Örneğin, user objesiyle veritabanında veya bir dosyada saklanan kullanıcı adı ve şifreleri karşılaştırabilirsiniz
            //Doğruysa true, değilse diğer satırları kontrol etmeye devam et
            if (kullaniciAdi.equals(name) && sifre.equals(surname)) {
                PersonnelPage personelsayf = new PersonnelPage();
                Personnel personelobj = new Personnel();
                
                personelobj.setName(name);
                personelobj.setSurname(surname);
                personelobj.setSalary(salary);
                personelobj.setAdvance(advance);
                personelsayf.getIDlabel().setText(String.valueOf(ID));
                personelsayf.getIsimLabel().setText(name);
                personelsayf.getSoyIsimLabel().setText(surname);
                personelsayf.getmaasLabel().setText(String.valueOf(salary));
                personelsayf.getadvanceLabel().setText(String.valueOf(advance));
                personelsayf.getstatusLabel().setText(status);

                personelsayf.setVisible(true);
                JOptionPane.showMessageDialog(this, "User login successful.");
                
                if(status!= null){
                    if (status.equals("Accepted.")) {
                    JOptionPane.showMessageDialog(rootPane, "Your advance request is accepted");
                }
                }
                
                // Login sayfasını kapat
                dispose();

                return true; //eğer doğru kullanıcı adı ve şifre varsa true döndür
                
            }
        }
        return false; //eğer doğru kullanıcı adı ve şifre yoksa false döndür
    }*/

    
    public boolean kullaniciGiris(String kullaniciAdi, String sifre) {
    String url = "jdbc:mysql://localhost:3306/proje";
    String username = "root";
    String pass = "arat";

    try (Connection conn = DriverManager.getConnection(url, username, pass)) {
        String query = "SELECT * FROM personnel WHERE ID = ? AND surname = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, kullaniciAdi);
        statement.setString(2, sifre);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            String surname = resultSet.getString("Surname");
            int salary = resultSet.getInt("Salary");
            int advance = resultSet.getInt("Advance_Value");
            String status = resultSet.getString("Request_Status");

            PersonnelPage personelsayf = new PersonnelPage();
            Personnel personelobj = new Personnel();
            personelobj.setName(name);
            personelobj.setSurname(surname);
            personelobj.setSalary(salary);
            personelobj.setAdvance(advance);
            personelsayf.getIDlabel().setText(String.valueOf(ID));
            personelsayf.getIsimLabel().setText(name);
            personelsayf.getSoyIsimLabel().setText(surname);
            personelsayf.getmaasLabel().setText(String.valueOf(salary));
            personelsayf.getadvanceLabel().setText(String.valueOf(advance));
            personelsayf.getstatusLabel().setText(status);

            personelsayf.setVisible(true);
            JOptionPane.showMessageDialog(this, "Kullanıcı girişi başarılı.");

            if (status != null) {
                if (status.equals("Kabul Edildi.")) {
                    JOptionPane.showMessageDialog(rootPane, "Avans talebiniz kabul edildi.");
                }
            }

            // Login sayfasını kapat
            dispose();

            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
    public boolean managerGiris(String kullaniciAdi, String sifre) {
    String url = "jdbc:mysql://localhost:3306/proje";
    String username = "root";
    String pass = "arat";

    try (Connection conn = DriverManager.getConnection(url, username, pass)) {
        String query = "SELECT * FROM manager WHERE ID = ? AND surname = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, kullaniciAdi);
        statement.setString(2, sifre);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            String surname = resultSet.getString("Surname");

            ManagerPage managerSayf = new ManagerPage();
            Manager managerObj = new Manager();
            managerObj.setName(name);
            managerObj.setSurname(surname);
            managerSayf.getIDlabel().setText(String.valueOf(ID));
            managerSayf.getIsimLabel().setText(name);
            managerSayf.getSoyIsimLabel().setText(surname);

            managerSayf.setVisible(true);
            JOptionPane.showMessageDialog(this, "Kullanıcı girişi başarılı.");

            // Login sayfasını kapat
            dispose();

            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

    
    
    public boolean adminGiris(String kullaniciAdi, String sifre) {
        // Kullanıcı adı ve şifre doğrulaması yapılabilir

        String url = "jdbc:mysql://localhost:3306/proje";
    String username = "root";
    String pass = "arat";

    try (Connection conn = DriverManager.getConnection(url, username, pass)) {
        String query = "SELECT * FROM admin WHERE ID = ? AND Username = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, kullaniciAdi);
        statement.setString(2, sifre);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String username1 = resultSet.getString("username");

            AdminPage adminSayf = new AdminPage();
            Admin adminObj = new Admin();
            adminObj.setUsername(username1);
            adminSayf.getIDlabel().setText(String.valueOf(ID));
            adminSayf.getUsernameLabel().setText(username1);

            adminSayf.setVisible(true);
            JOptionPane.showMessageDialog(this, "Kullanıcı girişi başarılı.");

            // Login sayfasını kapat
            dispose();

            return true;
        } else {
            Admin adminObj = new Admin();
            if (kullaniciAdi.equals(adminObj.getUsername()) && sifre.equals(adminObj.getPassword())) {
                AdminPage adminSayf = new AdminPage();
                adminSayf.getUsernameLabel().setText(kullaniciAdi);
                adminSayf.setVisible(true);
                JOptionPane.showMessageDialog(this, "Kullanıcı girişi başarılı.");

                // Login sayfasını kapat
                dispose();

                return true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
    }
 
}
