/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cp2labproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Monster
 */
public class PersonnelPage extends javax.swing.JFrame {

    public JLabel getIsimLabel() {
        return isimlbl;
    }

    public JLabel getSoyIsimLabel() {
        return soyisimlbl;
    }

    public JLabel getmaasLabel() {
        return maaslbl;
    }

    public JLabel getadvanceLabel() {
        return advancelbl;
    }

    public JLabel getstatusLabel() {
        return statuslbl;
    }
    public JLabel getIDlabel(){
        return IDlbl;
    }
    /**
     * Creates new form PersonnelPage
     */
    public PersonnelPage() {
        initComponents();
    }
    
    
    public PersonnelPage(String name,String surname,String salary, String advance, String status, String ID){
        this.isimlbl.setText(name);
        this.soyisimlbl.setText(surname);
        this.maaslbl.setText(salary);
        this.statuslbl.setText(status);
        this.IDlbl.setText(ID);
        this.advancelbl.setText(advance);
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        isimlbl = new javax.swing.JLabel();
        soyisimlbl = new javax.swing.JLabel();
        maaslbl = new javax.swing.JLabel();
        IDlbl = new javax.swing.JLabel();
        exitlbl = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        verifytxt = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        requestcombo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        sendbtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        statuslbl = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        advancelbl = new javax.swing.JLabel();
        textField = new javax.swing.JTextField();
        sendmsgbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Personnel Page");

        exitlbl.setText("LogOut");
        exitlbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitlblActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(verifytxt);

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 24)); // NOI18N
        jLabel1.setText("     REQUEST AREA");

        jLabel2.setText("Please write your name for verifying, and write your request value.");

        requestcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Click for selection", "Advance Request", "Leave Request", "Resignation Request", "Raise Salary Request" }));
        requestcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestcomboActionPerformed(evt);
            }
        });

        jLabel3.setText("Please select your request type");

        sendbtn.setText("Send your request");
        sendbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Surname");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Your Salary");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Your Advance Value");

        jLabel10.setText("Request Status");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Your ID number");

        textField.setText("Your Message");

        sendmsgbtn.setText("Send Message to Admin");
        sendmsgbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendmsgbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(382, 382, 382)
                            .addComponent(statuslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(requestcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sendbtn)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(soyisimlbl, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(isimlbl, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(maaslbl, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 151, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(exitlbl))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IDlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(advancelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sendmsgbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(isimlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exitlbl))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(soyisimlbl, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(statuslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(maaslbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(advancelbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendmsgbtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(requestcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sendbtn)))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void requestcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestcomboActionPerformed

    }//GEN-LAST:event_requestcomboActionPerformed

    private void exitlblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitlblActionPerformed

        int YesOrNo = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "LogOut", JOptionPane.YES_NO_OPTION);
        if (YesOrNo == 0) {
            dispose();
            UserLogin loginpage = new UserLogin();
            loginpage.setVisible(true);
        }
    }//GEN-LAST:event_exitlblActionPerformed

    private void sendbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbtnActionPerformed
        String expression = verifytxt.getText();
        String[] parts = expression.split(",");
        String name = parts[0];
        if (name.equals(IDlbl.getText())) {
            JOptionPane.showMessageDialog(rootPane, "The name is verified!");
            int choice = JOptionPane.showOptionDialog(rootPane, "Are you sure for your request?", "Request Check",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (choice == JOptionPane.YES_OPTION) {
                if (requestcombo.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Please select one of them.");
                }
                if (requestcombo.getSelectedIndex() == 1) {
                    AdminPage.advancerequestlistmodel.addElement(verifytxt.getText());
                    JOptionPane.showMessageDialog(rootPane, "Request has sent successfully.");
                    writeToFile("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\advance_requests.txt", verifytxt.getText());
                }
                if (requestcombo.getSelectedIndex() == 2) {
                    AdminPage.leaverequestlistmodel.addElement(verifytxt.getText());
                    JOptionPane.showMessageDialog(rootPane, "Request has sent successfully.");
                    writeToFile("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\leave_requests.txt", verifytxt.getText());
                }
                if (requestcombo.getSelectedIndex() == 3) {
                    AdminPage.resignationlistmodel.addElement(verifytxt.getText());
                    JOptionPane.showMessageDialog(rootPane, "Request has sent successfully.");
                    writeToFile("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\resignation_requests.txt", verifytxt.getText());
                }
                if (requestcombo.getSelectedIndex() == 4) {
                    AdminPage.raiselistmodel.addElement(verifytxt.getText());
                    JOptionPane.showMessageDialog(rootPane, "Request has sent successfully.");
                    writeToFile("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\raise_requests.txt", verifytxt.getText());
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "The action was canceled.");

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "You have wroten wrong name.");
        }
    }//GEN-LAST:event_sendbtnActionPerformed

    private void sendmsgbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendmsgbtnActionPerformed
        String metin = textField.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Monster\\Desktop\\FileApiNtfcn\\persmessage.txt"))) {
            writer.write(metin);
            System.out.println("Metin dosyaya yazildi.");
        } catch (IOException ex) {
            System.out.println("Dosya yazma hatasi: " + ex.getMessage());
        }
    }//GEN-LAST:event_sendmsgbtnActionPerformed

    /**
     * @param args the command line arguments
     */
    
    private void writeToFile(String filename, String content) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(content);
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public static void main(String args[]) {

        Personnel personel = new Personnel();

        JLabel isimlbl = new JLabel();
        isimlbl.setText(personel.getName());
        JLabel soyisimlbl = new JLabel();
        soyisimlbl.setText(personel.getSurname());
        JLabel maaslbl = new JLabel();
        int maas = personel.getSalary();
        String maasString = Integer.toString(maas);
        maaslbl.setText(maasString);
        JLabel advancelbl = new JLabel();

        int avans = personel.getAdvance();
        String avansString = Integer.toString(avans);
        advancelbl.setText(avansString);

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
            java.util.logging.Logger.getLogger(PersonnelPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonnelPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonnelPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonnelPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PersonnelPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDlbl;
    private javax.swing.JLabel advancelbl;
    private javax.swing.JButton exitlbl;
    private javax.swing.JLabel isimlbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel maaslbl;
    private javax.swing.JComboBox<String> requestcombo;
    private javax.swing.JButton sendbtn;
    private javax.swing.JButton sendmsgbtn;
    private javax.swing.JLabel soyisimlbl;
    private javax.swing.JLabel statuslbl;
    private javax.swing.JTextField textField;
    private javax.swing.JTextPane verifytxt;
    // End of variables declaration//GEN-END:variables
}
