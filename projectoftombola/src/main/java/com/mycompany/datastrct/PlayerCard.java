/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.datastrct;

import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mehdiarat
 */
public class PlayerCard extends javax.swing.JFrame {

    public static DefaultListModel sumtmb = new DefaultListModel();
    public static DefaultListModel sumbng = new DefaultListModel();
    public static DefaultListModel plyrtmb = new DefaultListModel(); // Verileri üstteki iki listten alıcaz.
    public static DefaultListModel plyrbng = new DefaultListModel();

    // playerCards değişkenini GameWindow sınıfında tanımladık
    /**
     * Creates new form PlayerCard
     */
    
    private int numPlayerspl;
    public PlayerCard() {
        initComponents();
        listtotaltombala.setModel(sumtmb);
        listtotalbingo.setModel(sumbng);
        tombalaplayerlist.setModel(plyrtmb);
        bingoplayerlist.setModel(plyrbng);

    }
    
    public PlayerCard(int numpl) {
        initComponents();
        this.numPlayerspl=numpl;
        listtotaltombala.setModel(sumtmb);
        listtotalbingo.setModel(sumbng);
        tombalaplayerlist.setModel(plyrtmb);
        bingoplayerlist.setModel(plyrbng);
         for (int i = 0; i < numPlayerspl; i++) {
        playercombo.addItem(Integer.toString(i + 1));
    }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkbox1 = new java.awt.Checkbox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listtotaltombala = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listtotalbingo = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tombalaplayerlist = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        bingoplayerlist = new javax.swing.JList<>();
        playercombo = new javax.swing.JComboBox<>();
        selectbtn = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        closebtn = new javax.swing.JButton();

        checkbox1.setLabel("checkbox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listtotaltombala.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listtotaltombala);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Total Tombalas", jPanel1);

        listtotalbingo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listtotalbingo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Total Bingos", jPanel2);

        jScrollPane3.setViewportView(tombalaplayerlist);

        jSplitPane1.setLeftComponent(jScrollPane3);

        jScrollPane4.setViewportView(bingoplayerlist);

        jSplitPane1.setRightComponent(jScrollPane4);

        for (int i = 0; i < numPlayerspl; i++) {
            playercombo.addItem(Integer.toString(i + 1));

        }

        selectbtn.setText("Select the player");
        selectbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectbtnActionPerformed(evt);
            }
        });

        jLabel23.setText("Tombalas");

        jLabel24.setText("Bingos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(playercombo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectbtn)
                        .addGap(56, 56, 56))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24))
                            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(73, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playercombo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectbtn))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tombalas and Bingos Specific to Player", jPanel4);

        closebtn.setText("Close");
        closebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closebtn)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(closebtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebtnActionPerformed
        // TODO add your handling code here:
        dispose();

    }//GEN-LAST:event_closebtnActionPerformed

    private void selectbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectbtnActionPerformed
        // TODO add your handling code here:
        Object selectedItemObj = playercombo.getSelectedItem();
        if (selectedItemObj != null) { // Seçilen öğe null değilse devam et
            String selectedItem = selectedItemObj.toString(); // Seçilen öğeyi String olarak al
            try {
                int selectedPlayer = Integer.parseInt(selectedItem); // String'i Integer'a dönüştür
                findplayerbng(selectedPlayer);
                findplayertombola(selectedPlayer);// Oyuncunun kartlarını göster
            } catch (NumberFormatException e) {
                // Seçilen öğe Integer'a dönüştürülemediği için hata oluştu
                // Bu durumda bir hata mesajı gösterebilir veya uygun bir işlem yapabilirsiniz
                System.err.println("Hata: Seçilen öğe bir sayıya dönüştürülemedi!");
            }
        } else {
            // Seçilen öğe null ise bir işlem yapma veya hata mesajı gösterme
            System.err.println("Hata: Bir öğe seçilmedi!");
        }
    }//GEN-LAST:event_selectbtnActionPerformed

    /* public void showplayerCardsframe(int playerIndex) {
    JFrame frame = new JFrame();
    frame.setTitle("Player " + playerIndex + "'s Cards");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new GridLayout(3, 9)); // 3 satır, 9 sütunlu bir grid oluştur
    MultiLinkedList currentList = GameWindow.playerCards;
    for (int i = 1; i <= playerIndex; i++) {
        if (currentList == null) {
            // Belirtilen oyuncu numarasına sahip bir kart listesi yoksa hata mesajı yazdır ve geri dön
            System.out.println("Player " + playerIndex + " does not exist.");
            return;
        }
        currentList = currentList.next; // Bir sonraki oyuncunun kart listesine geç
    }

    if (currentList == null) {
        // Belirtilen oyuncu numarasına sahip bir kart listesi yoksa hata mesajı yazdır ve geri dön
        System.out.println("Player " + playerIndex + " does not exist.");
        return;
    }

    NodeClass current = currentList.head;
    while (current != null) {
        JLabel label = new JLabel();
        if (current.data == -1) {
            label.setText("X");
        } else {
            label.setText(Integer.toString(current.data));
        }
        panel.add(label); // Label'i panele ekle
        current = current.down; // Bir sonraki düğüme geç
    }

    frame.add(panel); // Paneli frame'e ekle
    frame.pack(); // Frame boyutunu ayarla
    frame.setVisible(true); // Frame'i görünür yap
}
     */
 /*public void showplayerCardsframe (int playerIndex){
        
              

        MultiLinkedList currentList = GameWindow.playerCards;
        for (int i = 1; i <= playerIndex; i++) {
            if (currentList == null) {
                // Belirtilen oyuncu numarasına sahip bir kart listesi yoksa hata mesajı yazdır ve geri dön
                System.out.println("Player " + playerIndex + " does not exist.");
                return;
            }
            currentList = currentList.next; // Bir sonraki oyuncunun kart listesine geç
        }

        if (currentList == null) {
            // Belirtilen oyuncu numarasına sahip bir kart listesi yoksa hata mesajı yazdır ve geri dön
            System.out.println("Player " + playerIndex + " does not exist.");
            return;
        }

        // Belirtilen oyuncu numarasına sahip kart listesini bulduk
        // Bu listenin içeriğini yazdır
        currentList.printList2(this);
        

    }*/
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
            java.util.logging.Logger.getLogger(PlayerCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerCard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> bingoplayerlist;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JButton closebtn;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> listtotalbingo;
    private javax.swing.JList<String> listtotaltombala;
    private javax.swing.JComboBox<String> playercombo;
    private javax.swing.JButton selectbtn;
    private javax.swing.JList<String> tombalaplayerlist;
    // End of variables declaration//GEN-END:variables

    private void findplayerbng(int selectedPlayer) {

        for (int i = 0; i < sumbng.size(); i++) {
            String element = (String) sumbng.getElementAt(i);
            if (element.startsWith("Player" + selectedPlayer)) {
                boolean exists = false;
                // Aynı index'e sahip öğe zaten plyrbng'de var mı kontrol ediyoruz
                for (int j = 0; j < plyrbng.size(); j++) {
                    String plyrbngElement = (String) plyrbng.getElementAt(j);
                    if (plyrbngElement.startsWith("Player" + selectedPlayer)) {
                        exists = true;
                        plyrbng.set(j, element); // Eğer varsa güncelleme yap
                        break;
                    }
                }
                if (!exists) {
                    plyrbng.clear();
                    plyrbng.addElement(element); // Eğer yoksa ekleme yap
                }
            }
        }
    }

    private void findplayertombola(int selectedPlayer) {
        plyrtmb.clear();
        
        for (int i = 0; i < sumtmb.size(); i++) {
            Object object = sumtmb.getElementAt(i);
            String element = String.valueOf(object);
           
            if (element.startsWith("Player " + selectedPlayer)) {
                plyrtmb.addElement(element);
            }
        }
    }
}