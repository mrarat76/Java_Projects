/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cp2labproject;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

/**
 *
 * @author Monster
 */
public class PersonnelAddForManager extends javax.swing.JFrame {

    /**
     * Creates new form PersonnelAddForManager
     */
    public PersonnelAddForManager() {
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

        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nametxt = new javax.swing.JTextField();
        surnametxt = new javax.swing.JTextField();
        idtxt = new javax.swing.JTextField();
        salarytxt = new javax.swing.JTextField();
        advancetxt = new javax.swing.JTextField();
        cmbpers = new javax.swing.JComboBox<>();
        applybtn = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Personnel Affair Page");

        jLabel2.setText("Name");

        jLabel4.setText("Surname");

        jLabel5.setText("ID");

        jLabel6.setText("Salary");

        jLabel7.setText("Advance Value");

        cmbpers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Add", "Remove", "Update by ID" }));

        applybtn.setText("Apply");
        applybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applybtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(advancetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38)
                                    .addComponent(idtxt))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(salarytxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(surnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbpers, 0, 113, Short.MAX_VALUE)
                                    .addComponent(applybtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbpers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(surnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(applybtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(idtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salarytxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(advancetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void applybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applybtnActionPerformed
        // TODO add your handling code here:
        if (cmbpers.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please select an item.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (cmbpers.getSelectedIndex() == 1) {
            String name = nametxt.getText();
            String surname = surnametxt.getText();
            String str = idtxt.getText(); // Metottan gelen string değer
            int ID = Integer.parseInt(str);
            String salary = salarytxt.getText();
            String advance = advancetxt.getText();
            addPerson(name, surname, salary, advance, ID);
        } else if (cmbpers.getSelectedIndex() == 2) {
            String str = idtxt.getText(); // Metottan gelen string değer
            int ID = Integer.parseInt(str);
            DeletePerson(ID);

        } else if (cmbpers.getSelectedIndex() == 3) {
            String name = nametxt.getText();
            String surname = surnametxt.getText();
            String str = idtxt.getText(); // Metottan gelen string değer
            int ID = Integer.parseInt(str);
            String salary = salarytxt.getText();
            String advance = advancetxt.getText();
            UpdatePerson(name, surname, salary, advance, ID);
        }
    }//GEN-LAST:event_applybtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public void addPerson(String name, String surname, String salary, String advance_value, int ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Cp2LabProject_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Personnel_1 manager = new Personnel_1();
            manager.setId(ID);
            manager.setName(name);
            manager.setSurname(surname);
            manager.setSalary(salary);
            manager.setAdvanceValue(advance_value);
            em.persist(manager);

            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(this, "You cannot enter same ID!!!!'", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ex.printStackTrace();
            }
        } finally {
            em.close();
            emf.close();
        }
    }

    public void DeletePerson(int ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Cp2LabProject_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        Personnel_1 manager = em.find(Personnel_1.class, ID);
        em.getTransaction().begin();

        em.remove(manager);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public void UpdatePerson(String name, String surname, String salary, String advance_value, int ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Cp2LabProject_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Personnel_1 manager = em.find(Personnel_1.class, ID);
        manager.setName(name);
        manager.setSurname(surname);
        manager.setAdvanceValue(advance_value);
        manager.setSalary(salary);
        em.getTransaction().commit();

        em.close();
        emf.close();

    }

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
            java.util.logging.Logger.getLogger(PersonnelAddForManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonnelAddForManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonnelAddForManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonnelAddForManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonnelAddForManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField advancetxt;
    private javax.swing.JButton applybtn;
    private javax.swing.JComboBox<String> cmbpers;
    private javax.swing.JTextField idtxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField nametxt;
    private javax.swing.JTextField salarytxt;
    private javax.swing.JTextField surnametxt;
    // End of variables declaration//GEN-END:variables
}
