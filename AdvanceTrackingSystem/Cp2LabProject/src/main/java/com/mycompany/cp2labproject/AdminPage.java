/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cp2labproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Monster
 */
public class AdminPage extends javax.swing.JFrame {

    public static ArrayList<PersonnelPage> personnellistfeature = new ArrayList<>();
    public static DefaultTableModel personnelmodel = new DefaultTableModel();
    public static DefaultTableModel managermodel = new DefaultTableModel();
    public static DefaultTableModel adminmodel = new DefaultTableModel();
    public static DefaultListModel advancerequestlistmodel = new DefaultListModel();
    public static DefaultListModel leaverequestlistmodel = new DefaultListModel();
    public static DefaultListModel resignationlistmodel = new DefaultListModel();
    public static DefaultListModel raiselistmodel = new DefaultListModel();

    public JLabel getUsernameLabel() {
        return usernmelbl;
    }

    public JLabel getIDlabel() {
        return IDadmnlbl;
    }

    /**
     * Creates new form AdminPage
     */
    public AdminPage() {
        initComponents();

    }
    String url = "jdbc:mysql://localhost:3306/proje ";
    String username = "root"; // Kullanıcı adını buraya girin
    String password = "arat";

    private void loadPersonnelData() {
        personnelmodel.setColumnIdentifiers(new String[]{"ID", "Name", "Surname", "Salary", " Advance Value", "Request Status"});
        personnelmodel.setRowCount(0);

        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM personnel")) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String salary = resultSet.getString("salary");
                String advance = resultSet.getString("Advance_Value");
                String requeststatus = resultSet.getString("Request_Status");
                personnelmodel.addRow(new Object[]{id, name, surname, salary, advance, requeststatus});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadManagerData() {
        managermodel.setColumnIdentifiers(new String[]{"ID", "Name", "Surname"});
        managermodel.setRowCount(0);

        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT id, name, surname FROM manager")) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                managermodel.addRow(new Object[]{id, name, surname});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadAdminData() {
        adminmodel.setColumnIdentifiers(new String[]{"ID", "Username"});
        adminmodel.setRowCount(0);

        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT id, username FROM admin")) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String Username = resultSet.getString("Username");
                adminmodel.addRow(new Object[]{id, Username});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addPersonnel(int ID, String name, String surname, int salary, int advance) {
        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement()) {

            String query = "INSERT INTO personnel (ID, name, surname, salary, Advance_Value, Request_Status) VALUES "
                    + "('" + ID + "', '" + name + "', '" + surname + "', " + salary + ", " + advance + ", '')";

            statement.executeUpdate(query);
        } catch (SQLException ex) {
            if (ex instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(this, "You cannot enter same id! Please refresh your table with switch button!'", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ex.printStackTrace();
            }
        }
    }

    private void addManager(int ID, String name, String surname) {
        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement()) {

            String query = "INSERT INTO manager (ID, name, surname) VALUES "
                    + "('" + ID + "', '" + name + "', '" + surname + "')";

            statement.executeUpdate(query);
        } catch (SQLException ex) {
            if (ex instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(this, "You cannot enter same id! Please refresh your table with switch button!'", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ex.printStackTrace();
            }
        }
    }

    private void addAdmin(int ID, String name) {
        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement()) {

            String query = "INSERT INTO admin (ID, username) VALUES "
                    + "('" + ID + "', '" + name + "')";

            statement.executeUpdate(query);
        } catch (SQLException ex) {
            if (ex instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(this, "You cannot enter same id! Please refresh your table with switch button!'", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ex.printStackTrace();
            }
        }
    }

    public static void sendMessage(String message) {
        JOptionPane.showMessageDialog(null, "Yeni mesaj: " + message);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        personneltbl = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        surnametxt = new javax.swing.JTextPane();
        addbtn = new javax.swing.JButton();
        messagebtn = new javax.swing.JButton();
        salarytxt = new javax.swing.JTextField();
        nametxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        removebtn = new javax.swing.JButton();
        advancetxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        exitlbl = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        changeablelist = new javax.swing.JList<>();
        advanceradio = new javax.swing.JRadioButton();
        leaveradio = new javax.swing.JRadioButton();
        bringbtn = new javax.swing.JButton();
        confirmbtn = new javax.swing.JButton();
        resignradio = new javax.swing.JRadioButton();
        raiseradio = new javax.swing.JRadioButton();
        notifitebtn = new javax.swing.JButton();
        statusresetbtn = new javax.swing.JButton();
        declinebtn = new javax.swing.JButton();
        Updatebtn = new javax.swing.JButton();
        IDtxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        persradio = new javax.swing.JRadioButton();
        mngrradio = new javax.swing.JRadioButton();
        adminradio = new javax.swing.JRadioButton();
        switchbtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        usernmelbl = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        IDadmnlbl = new javax.swing.JLabel();
        shwmsgfrompersbtn = new javax.swing.JButton();
        dltmessgfrompers = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Page");

        personneltbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null"
            }
        ));
        jScrollPane1.setViewportView(personneltbl);

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel2.setText("    PERSONNEL LIST");

        jScrollPane2.setViewportView(surnametxt);

        addbtn.setText("Add");
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });

        messagebtn.setText("Show Messages");
        messagebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messagebtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Source Serif Pro Semibold", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Surname");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Salary");

        removebtn.setText("Remove");
        removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setText("Advance Value");

        exitlbl.setText("LogOut");
        exitlbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitlblActionPerformed(evt);
            }
        });

        changeablelist.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Please select request." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(changeablelist);

        buttonGroup4.add(advanceradio);
        advanceradio.setText("Advance Requests");

        buttonGroup4.add(leaveradio);
        leaveradio.setText("Leave Requests");

        bringbtn.setText("Bring the selected list");
        bringbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bringbtnActionPerformed(evt);
            }
        });

        confirmbtn.setText("Confirm Selection");
        confirmbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmbtnActionPerformed(evt);
            }
        });

        buttonGroup4.add(resignradio);
        resignradio.setText("Resignation Requests");

        buttonGroup4.add(raiseradio);
        raiseradio.setText("Raise Salary Requests");
        raiseradio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raiseradioActionPerformed(evt);
            }
        });

        notifitebtn.setText("Show notifications");
        notifitebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notifitebtnActionPerformed(evt);
            }
        });

        statusresetbtn.setText("Status Reset by ID");
        statusresetbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusresetbtnActionPerformed(evt);
            }
        });

        declinebtn.setText("Decline Selection");
        declinebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declinebtnActionPerformed(evt);
            }
        });

        Updatebtn.setText("Update");
        Updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatebtnActionPerformed(evt);
            }
        });

        IDtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDtxtActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Source Serif Pro Semibold", 1, 12)); // NOI18N
        jLabel6.setText("ID");

        buttonGroup6.add(persradio);
        persradio.setText("Personnel Table");
        persradio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                persradioActionPerformed(evt);
            }
        });

        buttonGroup6.add(mngrradio);
        mngrradio.setText("Manager Table");

        buttonGroup6.add(adminradio);
        adminradio.setText("Admin Table");

        switchbtn.setText("Switch table");
        switchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchbtnActionPerformed(evt);
            }
        });

        deletebtn.setText("Delete Messages");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        jLabel7.setText("Admin Username:");

        jLabel8.setText("Admin ID");

        shwmsgfrompersbtn.setText("Shw. Personnel Msg");
        shwmsgfrompersbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shwmsgfrompersbtnActionPerformed(evt);
            }
        });

        dltmessgfrompers.setText("Delete Msg. from Pers.");
        dltmessgfrompers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dltmessgfrompersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(salarytxt)
                                    .addComponent(jScrollPane2)
                                    .addComponent(nametxt)
                                    .addComponent(advancetxt, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(IDtxt)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(adminradio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(switchbtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(persradio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mngrradio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(IDadmnlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(usernmelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(shwmsgfrompersbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dltmessgfrompers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(exitlbl))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(addbtn)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(removebtn))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(messagebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(deletebtn)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(114, 114, 114)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(notifitebtn)
                                                            .addComponent(bringbtn))
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(declinebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(confirmbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(resignradio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(advanceradio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(statusresetbtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(leaveradio, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(Updatebtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(raiseradio)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)))
                                .addGap(19, 19, 19))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exitlbl)
                            .addComponent(notifitebtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(advanceradio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leaveradio)
                                .addGap(1, 1, 1)
                                .addComponent(resignradio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(raiseradio)
                                .addGap(18, 18, 18)
                                .addComponent(bringbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernmelbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(confirmbtn)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8)
                                        .addComponent(persradio))
                                    .addComponent(IDadmnlbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(declinebtn)
                                    .addComponent(mngrradio)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adminradio)
                            .addComponent(switchbtn)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IDtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(messagebtn)
                            .addComponent(shwmsgfrompersbtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deletebtn)
                            .addComponent(dltmessgfrompers))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusresetbtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(Updatebtn)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addbtn)
                                    .addComponent(removebtn)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(salarytxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(advancetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(136, 136, 136)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
        try {
            String name = nametxt.getText();
            if (name.isEmpty()) {
                throw new NumberFormatException();
            }

            String IDStr = IDtxt.getText();
            if (IDStr.isEmpty()) {
                throw new NumberFormatException();
            }

            int ID = Integer.parseInt(IDStr);

            if (adminradio.isSelected()) {
                addAdmin(ID, name);
                Object[] row = {ID, name};
                adminmodel.addRow(row);
            } else if (persradio.isSelected()) {
                String Surname = surnametxt.getText();
                if (Surname.isEmpty()) {
                    throw new NumberFormatException();
                }

                if (name.matches(".*\\d.*") || Surname.matches(".*\\d.*")) {
                    throw new NumberFormatException();
                }

                String salarystr = salarytxt.getText();
                int salary = Integer.parseInt(salarystr);
                String advancestr = advancetxt.getText();
                int advance = Integer.parseInt(advancestr);

                addPersonnel(ID, name, Surname, salary, advance);
                Object[] row = {ID, name, Surname, salary, advance};
                personnelmodel.addRow(row);
            } else if (mngrradio.isSelected()) {
                String Surname = surnametxt.getText();
                if (Surname.isEmpty()) {
                    throw new NumberFormatException();
                }

                if (name.matches(".*\\d.*") || Surname.matches(".*\\d.*")) {
                    throw new NumberFormatException();
                }

                addManager(ID, name, Surname);
                Object[] row = {ID, name, Surname};
                managermodel.addRow(row);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Yanlış değer girdiniz");
        }

    }//GEN-LAST:event_addbtnActionPerformed

    private void removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebtnActionPerformed
        int selectedrow = personneltbl.getSelectedRow();
        if (selectedrow >= 0) {
            Object value = personneltbl.getValueAt(selectedrow, 0);
            int ID = Integer.parseInt(value.toString());

            if (adminradio.isSelected()) {
                // Veritabanından admini sil
                removeAdmin(ID);
                adminmodel.removeRow(selectedrow);
            } else if (persradio.isSelected()) {
                // Veritabanından personeli sil
                removePersonnel(ID);
                personnelmodel.removeRow(selectedrow);
            } else if (mngrradio.isSelected()) {
                // Veritabanından yöneticiyi sil
                removeManager(ID);
                managermodel.removeRow(selectedrow);
            }
        }

    }//GEN-LAST:event_removebtnActionPerformed

    private void exitlblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitlblActionPerformed

        int YesOrNo = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "LogOut", JOptionPane.YES_NO_OPTION);
        if (YesOrNo == 0) {
            dispose();
            UserLogin loginpage = new UserLogin();
            loginpage.setVisible(true);
        }


    }//GEN-LAST:event_exitlblActionPerformed

    private void bringbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bringbtnActionPerformed
        if (advanceradio.isSelected()) {
            changeablelist.setModel(advancerequestlistmodel);

        }
        if (leaveradio.isSelected()) {
            changeablelist.setModel(leaverequestlistmodel);
        }
        if (resignradio.isSelected()) {
            changeablelist.setModel(resignationlistmodel);
        }
        if (raiseradio.isSelected()) {
            changeablelist.setModel(raiselistmodel);
        }
    }//GEN-LAST:event_bringbtnActionPerformed

    /*private void removePersonFromDatabase(String name, String surname) {
        String url = "jdbc:derby:C:\\Users\\Monster\\GlassFish_Server\\testdatabase;create=true";
        String tableName = "PERSONNELTABLE";

        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM " + tableName + " WHERE Name='" + name + "' AND Surname='" + surname + "'";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    private void removePersonnel(int ID) {

        String tableName = "personnel";

        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement()) {

            String query = "DELETE FROM " + tableName + " WHERE ID = " + ID;
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void removeManager(int ID) {
        String tableName = "manager";

        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement()) {

            String query = "DELETE FROM " + tableName + " WHERE ID = " + ID;
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void removeAdmin(int ID) {
        String tableName = "admin";

        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement()) {

            String query = "DELETE FROM " + tableName + " WHERE ID = " + ID;
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private void raiseradioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_raiseradioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_raiseradioActionPerformed

    private void confirmbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmbtnActionPerformed

        int confirm = JOptionPane.showConfirmDialog(rootPane, "Are you sure for your action?");
        if (confirm == JOptionPane.YES_OPTION) {

            if (advanceradio.isSelected()) {
                // İleriye taşıma işlemi

                String fileID = changeablelist.getSelectedValue();
                String selectedID = changeablelist.getSelectedValue().toString();
                String[] splitElement = selectedID.split(",");

                String name = splitElement[0];
                int requestedAdvance = Integer.parseInt(splitElement[1]);

                // Veritabanına bağlanma ve işlemler
                try ( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje", "root", "arat")) {
                    String updateQuery = "UPDATE personnel SET salary = salary - ?, advance_value = advance_value - ?, request_status = 'accepted' WHERE ID = ?";
                    try ( PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setInt(1, requestedAdvance);
                        statement.setInt(2, requestedAdvance);
                        statement.setString(3, name);
                        statement.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Hata durumunda uygun işlemleri yapın
                }

                // listmodel'den öğeyi kaldırma işlemi
                advancerequestlistmodel.removeElement(selectedID);

                // Dosyadan öğeyi kaldırma işlemi
                removeItemFromFile("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\advance_requests.txt", fileID);
            }
            if (raiseradio.isSelected()) {
                // Zam işlemi
                String selectedName = changeablelist.getSelectedValue();

                // Veritabanına bağlanma ve işlemler
                try ( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje", "root", "arat")) {
                    String updateQuery = "UPDATE personnel SET salary = salary + 500 WHERE ID = ?";
                    try ( PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, selectedName);
                        statement.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Hata durumunda uygun işlemleri yapın
                }
                raiselistmodel.removeElement(selectedName);

                removeItemFromFile("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\raise_requests.txt", selectedName);
            }

            if (resignradio.isSelected()) {
                // İstifa işlemi
                String selectedName = changeablelist.getSelectedValue();

                // Veritabanına bağlanma ve işlemler
                try ( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje_database", "kullanici_adi", "sifre")) {
                    String deleteQuery = "DELETE FROM personnel WHERE ID = ?";
                    try ( PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                        statement.setString(1, selectedName);
                        statement.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Hata durumunda uygun işlemleri yapın
                }
                resignationlistmodel.removeElement(selectedName);

                removeItemFromFile("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\resignation_requests.txt", selectedName);
            }
            if (leaveradio.isSelected()) {
                JOptionPane.showConfirmDialog(rootPane, "The system is not responsible for this action, please talk with your personel.");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "The action was canceled.");
        }

        /* int confirm = JOptionPane.showConfirmDialog(rootPane, "Are you sure for your action?");
        if (confirm == JOptionPane.YES_OPTION) {

            if (advanceradio.isSelected()) {
                String selectedID = changeablelist.getSelectedValue().toString();
                String[] splitElement = selectedID.split(",");
                String name = splitElement[0];
                int selectedRow = -1;
                for (int i = 0; i < personnelmodel.getRowCount(); i++) {
                    if (personnelmodel.getValueAt(i, 0).toString().equals(name)) {
                        selectedRow = i;

                        advancerequestlistmodel.removeElement(selectedID);
                        break;

                    }
                }
                if (selectedRow != -1) {
                    int salary = Integer.parseInt(personnelmodel.getValueAt(selectedRow, 2).toString());
                    int advance = Integer.parseInt(personnelmodel.getValueAt(selectedRow, 3).toString());
                    int requestedadvance = Integer.parseInt(splitElement[1]);
                    int newSalary = salary - requestedadvance;
                    int newAdvance = advance - requestedadvance;
                    personnelmodel.setValueAt(newSalary, selectedRow, 2);
                    personnelmodel.setValueAt(newAdvance, selectedRow, 3);
                    personnelmodel.setValueAt("Accepted.", selectedRow, 4);

                }
            }
            if (raiseradio.isSelected()) {
                String selectedName = changeablelist.getSelectedValue();
                int selectedRow = -1;
                for (int i = 0; i < personnelmodel.getRowCount(); i++) {
                    if (personnelmodel.getValueAt(i, 0).toString().equals(selectedName)) {
                        selectedRow = i;
                        break;
                    }
                }
                if (selectedRow != -1) {
                    int salary = Integer.parseInt(personnelmodel.getValueAt(selectedRow, 2).toString());

                    int newSalary = salary + 500;
                    personnelmodel.setValueAt(newSalary, selectedRow, 2);

                }

            }

            if (resignradio.isSelected()) {
                String selectedName = changeablelist.getSelectedValue();
                int selectedRow = -1;
                for (int i = 0; i < personnelmodel.getRowCount(); i++) {
                    if (personnelmodel.getValueAt(i, 0).toString().equals(selectedName)) {
                        selectedRow = i;
                        break;
                    }
                }
                if (selectedRow != -1) {
                    personnelmodel.removeRow(selectedRow);

                }
            }
            if (leaveradio.isSelected()) {
                JOptionPane.showConfirmDialog(rootPane, "The system is not responsible for this action, please talk with your personel.");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "The action was canceled.");
        }
         */

    }//GEN-LAST:event_confirmbtnActionPerformed

    private void notifitebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notifitebtnActionPerformed

        if (advancerequestlistmodel.size() != 0) {
            JOptionPane.showMessageDialog(null, "New advance requests, please check the list.");
        }
        if (leaverequestlistmodel.size() != 0) {
            JOptionPane.showMessageDialog(null, "New leave requests,please check the list ");
        }
        if (resignationlistmodel.size() != 0) {
            JOptionPane.showMessageDialog(null, "New resignation requests, please check the list.");
        }
        if (raiselistmodel.size() != 0) {
            JOptionPane.showMessageDialog(null, "New salary increase requests, please check the list.");
        } else {
            JOptionPane.showMessageDialog(null, "Yeeey! There is no new notificatons!");
        }
    }//GEN-LAST:event_notifitebtnActionPerformed

    private void messagebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messagebtnActionPerformed

        try ( BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\Message.txt"))) {
            StringBuilder metin = new StringBuilder();
            String satir;
            while ((satir = reader.readLine()) != null) {
                metin.append(satir).append("\n");
            }
            JOptionPane.showMessageDialog(null, metin.toString(), "Metin Dosyasi Icerigi", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.out.println("Dosya okuma hatasi: " + ex.getMessage());
        }

        // Veritabanı bağlantısı kurulması ve gerekli sorgunun oluşturulması gerekmektedir
        /*  for (int i = 0; i < personnelmodel.getRowCount(); i++) {
            if (personnelmodel.getValueAt(i, 0).toString().equals(nametxt.getText())) {
                personnelmodel.removeRow(i);
                break;
            }
        } */
    }//GEN-LAST:event_messagebtnActionPerformed

    private void statusresetbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusresetbtnActionPerformed
        String targetID = IDtxt.getText(); // JTextField'tan hedef soyadı alınır

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje", "root", "arat");
            Statement stmt = conn.createStatement();

            // Veritabanı sorgusu oluşturulması
            String query = "UPDATE personnel SET request_status = ' ' WHERE ID = '" + targetID + "'";

            // Sorgunun çalıştırılması
            stmt.executeUpdate(query);

            // Kapatılan kaynakların serbest bırakılması
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata durumunda gerekli hata işlemleri yapılabilir
        }

        /*for (int i = 0; i < personnelmodel.getRowCount(); i++) {
            if (personnelmodel.getValueAt(i, 0).toString().equals(surnametxt.getText())) {
                personnelmodel.setValueAt(" ", i, 4);
                break;
            }
        }*/

    }//GEN-LAST:event_statusresetbtnActionPerformed

    private void declinebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declinebtnActionPerformed

        int confirm = JOptionPane.showConfirmDialog(rootPane, "Are you sure for your action?");
        if (confirm == JOptionPane.YES_OPTION) {
            try ( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/proje", "root", "arat")) {
                if (advanceradio.isSelected()) {
                    String selectedName = changeablelist.getSelectedValue().toString();
                    String[] splitElement = selectedName.split(",");
                    String name = splitElement[0];

                    // Update the "personel" table to set the status as "Declined"
                    String updateQuery = "UPDATE personnel SET request_status = 'Declined' WHERE ID = ?";
                    try ( PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, name);
                        statement.executeUpdate();
                    }

                    advancerequestlistmodel.removeElement(selectedName);
                    removeItemFromFile("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\advance_requests.txt", selectedName);
                } else if (raiseradio.isSelected()) {
                    String selectedName = changeablelist.getSelectedValue();

                    // Remove the row from the "personel" table
                    String updateQuery = "UPDATE personnel SET request_status = 'Declined' WHERE ID = ?";
                    try ( PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, selectedName);
                        statement.executeUpdate();
                    }

                    raiselistmodel.removeElement(selectedName);
                    removeItemFromFile("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\advance_requests.txt", selectedName);
                } else if (resignradio.isSelected()) {
                    String selectedName = changeablelist.getSelectedValue();

                    // Remove the row from the "personel" table
                    String deleteQuery = "DELETE FROM personnel WHERE ID = ?";
                    try ( PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                        statement.setString(1, selectedName);
                        statement.executeUpdate();
                    }
                } else if (leaveradio.isSelected()) {
                    JOptionPane.showConfirmDialog(rootPane, "The system is not responsible for this action, please talk with your personel.");
                }
            } catch (SQLException ex) {
                // Handle any errors that may occur during the database operations
                ex.printStackTrace();
                // You can show an error message dialog here if desired
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "The action was canceled.");
        }

        /* int confirm = JOptionPane.showConfirmDialog(rootPane, "Are you sure for your action?");
        if (confirm == JOptionPane.YES_OPTION) {

            if (advanceradio.isSelected()) {
                String selectedName = changeablelist.getSelectedValue().toString();
                String[] splitElement = selectedName.split(",");
                String name = splitElement[0];
                int selectedRow = -1;
                for (int i = 0; i < personnelmodel.getRowCount(); i++) {
                    if (personnelmodel.getValueAt(i, 0).toString().equals(name)) {
                        selectedRow = i;

                        advancerequestlistmodel.removeElement(selectedName);
                        break;

                    }
                }
                if (selectedRow != -1) {

                    personnelmodel.setValueAt("Declined.", selectedRow, 4);
                }
            }
            if (raiseradio.isSelected()) {
                String selectedName = changeablelist.getSelectedValue();
                int selectedRow = -1;
                for (int i = 0; i < personnelmodel.getRowCount(); i++) {
                    if (personnelmodel.getValueAt(i, 0).toString().equals(selectedName)) {
                        selectedRow = i;
                        advancerequestlistmodel.removeElement(selectedName);
                        break;
                    }
                }
                if (selectedRow != -1) {
                    personnelmodel.setValueAt("Declined.", selectedRow, 4);

                }

            }

            if (resignradio.isSelected()) {
                String selectedName = changeablelist.getSelectedValue();
                int selectedRow = -1;
                for (int i = 0; i < personnelmodel.getRowCount(); i++) {
                    if (personnelmodel.getValueAt(i, 0).toString().equals(selectedName)) {
                        selectedRow = i;
                        break;
                    }
                }
                if (selectedRow != -1) {
                    personnelmodel.removeRow(selectedRow);

                }
            }
            if (leaveradio.isSelected()) {
                JOptionPane.showConfirmDialog(rootPane, "The system is not responsible for this action, please talk with your personel.");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "The action was canceled.");
        }  */
    }//GEN-LAST:event_declinebtnActionPerformed

    private void UpdatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatebtnActionPerformed
        String idText = IDtxt.getText();
        int ID = Integer.parseInt(idText);

        String newName = nametxt.getText();
        String newSurname = surnametxt.getText();

        if (adminradio.isSelected()) {
            // Sadece ID ve yeni ad değerlerini kullanarak admini güncelle
            updateAdmin(ID, newName);

            // Diğer alanları temizle veya gerekli işlemleri yap
            salarytxt.setText("");
            advancetxt.setText("");
        } else if (mngrradio.isSelected()) {
            // Sadece ID, yeni ad ve yeni soyad değerlerini kullanarak yöneticiyi güncelle
            String newManagerSurname = surnametxt.getText();
            updateManager(ID, newName, newManagerSurname);

            // Diğer alanları temizle veya gerekli işlemleri yap
            salarytxt.setText("");
            advancetxt.setText("");
        } else if (persradio.isSelected()) {
            // ID, yeni ad, yeni soyad, yeni maaş ve yeni avans değerlerini kullanarak personeli güncelle
            String newSalaryText = salarytxt.getText();
            int newSalary = Integer.parseInt(newSalaryText);

            String newAdvanceText = advancetxt.getText();
            int newAdvance;
            try {
                newAdvance = Integer.parseInt(newAdvanceText);
            } catch (NumberFormatException e) {
                // Hatalı bir değer girildiğinde veya boş bir değer olduğunda hata durumunu yönetin
                newAdvance = 0; // Varsayılan olarak 0 atandı
            }

            updatePersonnel(ID, newName, newSurname, newSalary, newAdvance);
        }
    }//GEN-LAST:event_UpdatebtnActionPerformed

    private void IDtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDtxtActionPerformed

    private void switchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchbtnActionPerformed
        // TODO add your handling code here:
        if (persradio.isSelected()) {
            loadPersonnelData();
            personneltbl.setModel(personnelmodel);
        } else if (mngrradio.isSelected()) {
            loadManagerData();
            personneltbl.setModel(managermodel);
        } else if (adminradio.isSelected()) {
            loadAdminData();
            personneltbl.setModel(adminmodel);
        }

    }//GEN-LAST:event_switchbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\Message.txt"))) {
            writer.write("");
            System.out.println("Metin dosyadan silindi.");
        } catch (IOException ex) {
            System.out.println("Dosya yazma hatasi: " + ex.getMessage());
        }
    }//GEN-LAST:event_deletebtnActionPerformed

    private void persradioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_persradioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_persradioActionPerformed

    private void shwmsgfrompersbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shwmsgfrompersbtnActionPerformed
        // TODO add your handling code here:

        try ( BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\persmessage.txt"))) {
            StringBuilder metin = new StringBuilder();
            String satir;
            while ((satir = reader.readLine()) != null) {
                metin.append(satir).append("\n");
            }
            JOptionPane.showMessageDialog(null, metin.toString(), "Metin Dosyasi Icerigi", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.out.println("Dosya okuma hatasi: " + ex.getMessage());
        }
    }//GEN-LAST:event_shwmsgfrompersbtnActionPerformed

    private void dltmessgfrompersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dltmessgfrompersActionPerformed
        // TODO add your handling code here:
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\persmessage.txt"))) {
            writer.write("");
            System.out.println("Metin dosyadan silindi.");
        } catch (IOException ex) {
            System.out.println("Dosya yazma hatasi: " + ex.getMessage());
        }
    }//GEN-LAST:event_dltmessgfrompersActionPerformed
    /*public void updatePerson(int rowIndex, String name, String surname, String salary, String advanceValue) {
        String tableName = "personnel";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "username", "password");
            Statement s = connection.createStatement();

            // Seçilen satırın verisini al
            DefaultTableModel tableModel = (DefaultTableModel) personneltbl.getModel();
            String oldName = tableModel.getValueAt(rowIndex, 0).toString();
            String oldSurname = tableModel.getValueAt(rowIndex, 1).toString();

            // Kişiyi güncelle
            String sql = "UPDATE " + tableName + " SET Name='" + name + "', Surname='" + surname + "', Salary=" + salary
                    + ", AdvanceValue=" + advanceValue + ", RequestStatus='" + "" + "' WHERE Name='" + oldName
                    + "' AND Surname='" + oldSurname + "'";
            s.executeUpdate(sql);

            // Kişiyi JTable'da güncelle
            tableModel.setValueAt(name, rowIndex, 0);
            tableModel.setValueAt(surname, rowIndex, 1);
            tableModel.setValueAt(salary, rowIndex, 2);
            tableModel.setValueAt(advanceValue, rowIndex, 3);
            tableModel.setValueAt(" ", rowIndex, 4);

            s.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    private void updatePersonnel(int ID, String newName, String newSurname, int newSalary, int newAdvance) {

        String tableName = "personnel";

        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement()) {

            String query = "UPDATE " + tableName + " SET name = '" + newName + "', surname = '" + newSurname + "', salary = " + newSalary + ", Advance_Value = " + newAdvance + " WHERE ID = " + ID;
            statement.executeUpdate(query);
            int rowCount = personnelmodel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                int rowID = (int) personnelmodel.getValueAt(i, 0);
                if (rowID == ID) {
                    personnelmodel.setValueAt(newName, i, 1);
                    personnelmodel.setValueAt(newSurname, i, 2);
                    personnelmodel.setValueAt(newSalary, i, 3);
                    personnelmodel.setValueAt(newAdvance, i, 4);
                    break;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateAdmin(int ID, String newName) {
        String tableName = "admin";

        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement()) {

            String query = "UPDATE " + tableName + " SET name = '" + newName + "' WHERE ID = " + ID;
            statement.executeUpdate(query);

            int rowCount = adminmodel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                int rowID = (int) adminmodel.getValueAt(i, 0);
                if (rowID == ID) {
                    adminmodel.setValueAt(newName, i, 1);
                    break;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateManager(int ID, String newName, String newSurname) {
        String tableName = "manager";

        try ( Connection connection = DriverManager.getConnection(url, username, password);  Statement statement = connection.createStatement()) {

            String query = "UPDATE " + tableName + " SET name = '" + newName + "', surname = '" + newSurname + "' WHERE ID = " + ID;
            statement.executeUpdate(query);

            int rowCount = managermodel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                int rowID = (int) managermodel.getValueAt(i, 0);
                if (rowID == ID) {
                    managermodel.setValueAt(newName, i, 1);
                    managermodel.setValueAt(newSurname, i, 2);
                    break;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void removeItemFromFile(String filename, String idToRemove) {

        try {
            Path filePath = Path.of(filename);
            List<String> lines = Files.readAllLines(filePath);

            List<String> updatedLines = new ArrayList<>();

            for (String line : lines) {
                String[] splitElement = line.split(",");
                String id = splitElement[0].trim();

                if (!id.equals(idToRemove)) {
                    updatedLines.add(line);
                }
            }

            Files.write(filePath, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Satır başarıyla silindi.");
        } catch (IOException e) {
            e.printStackTrace();
            // Hata durumunda uygun işlemleri yapın
        }

        /* try {
            Path filePath = Path.of(filename);
            List<String> lines = Files.readAllLines(filePath);

            List<String> updatedLines = new ArrayList<>();

            for (String line : lines) {
                String[] splitElement = line.split(",");
                String id = splitElement[0].trim();

                if (!id.equals(idToRemove)) {
                    updatedLines.add(line);
                }
            }

            Files.write(filePath, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
           
        } catch (IOException e) {
            e.printStackTrace();
            // Hata durumunda uygun işlemleri yapın
        } */
    }

    public JTable getAdminTable() {
        return personneltbl;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDadmnlbl;
    private javax.swing.JTextField IDtxt;
    private javax.swing.JButton Updatebtn;
    private javax.swing.JButton addbtn;
    private javax.swing.JRadioButton adminradio;
    private javax.swing.JRadioButton advanceradio;
    private javax.swing.JTextField advancetxt;
    private javax.swing.JButton bringbtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JList<String> changeablelist;
    private javax.swing.JButton confirmbtn;
    private javax.swing.JButton declinebtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JButton dltmessgfrompers;
    private javax.swing.JButton exitlbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton leaveradio;
    private javax.swing.JButton messagebtn;
    private javax.swing.JRadioButton mngrradio;
    private javax.swing.JTextField nametxt;
    private javax.swing.JButton notifitebtn;
    private javax.swing.JTable personneltbl;
    private javax.swing.JRadioButton persradio;
    private javax.swing.JRadioButton raiseradio;
    private javax.swing.JButton removebtn;
    private javax.swing.JRadioButton resignradio;
    private javax.swing.JTextField salarytxt;
    private javax.swing.JButton shwmsgfrompersbtn;
    private javax.swing.JButton statusresetbtn;
    private javax.swing.JTextPane surnametxt;
    private javax.swing.JButton switchbtn;
    private javax.swing.JLabel usernmelbl;
    // End of variables declaration//GEN-END:variables
}
