/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastrct;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mehdiarat
 */
public class MultiLinkedList {
     NodeClass head;
     MultiLinkedList next; 

    public MultiLinkedList() {
        this.head = null;
    }

    // Method to add a new number to the linked list
    public void add(int data) {
        NodeClass newNode = new NodeClass(data);
        if (head == null) {
            head = newNode;
            return;
        }
        NodeClass current = head;
        while (current.down != null) {
            current = current.down;
        }
        current.down = newNode;
    }

    // Method to remove a number from the linked list
    public void remove(int data) {
        if (head == null)
            return;
        NodeClass current = head;
        NodeClass prev = null;
        while (current != null && current.data != data) {
            prev = current;
            current = current.down;
        }
        if (current == null)
            return;
        if (prev == null) {
            head = current.next;
        } else {
            prev.down = current.next;
        }
    }

    // Method to check if the linked list contains a specific number
    public boolean contains(int data) {
        NodeClass current = head;
        while (current != null) {
            if (current.data == data)
                return true;
            current = current.down;
        }
        return false;
    }

    
    
     public void printList() {
        NodeClass current = head;
        while (current != null) {
            if (current.data == -1) {
                System.out.print("X ");
            } else {
                System.out.print(current.data + " ");
            }
            current = current.down;
        }
        System.out.println();
    }

    
    
    
    
 /*   // Method to print the linked list
    public void printList() {
        NodeClass current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.down;
        }
        System.out.println();
    }*/

public void appendList(MultiLinkedList list) {
        if (list.head == null)
            return;
        NodeClass current = head;
        while (current.down != null) {
            current = current.down;
        }
        current.down = list.head;
    }

    public boolean isWinningCard() {
    NodeClass current = head;
    int count = 0;
    while (current != null) {
        count++;
        current = current.down;
    }
    return count == 15;
}
/*public void printList(JFrame frame) {
    NodeClass current = head;
    Container contentPane = frame.getContentPane(); // Mevcut içeriği al
    if (!(contentPane instanceof JPanel)) {
        // Eğer içerik panel değilse yeni bir panel oluştur
        contentPane = new JPanel(new GridLayout(3, 9));
        frame.setContentPane(contentPane); // Yeni paneli frame'in içeriği olarak ayarla
    } else {
        // Eğer içerik bir panel ise mevcut paneli kullan
        contentPane.removeAll(); // Önceki içeriği temizle
    }
    
    JPanel panel = (JPanel) contentPane;

    for (int i = 0; i < 3; i++) { // Her satır için
        for (int j = 0; j < 9; j++) { // Her sütun için
            JLabel label = new JLabel(); // Yeni bir JLabel oluştur
            if (current != null) {
                if (current.data == -1) {
                    label.setText("X"); // -1 ise "X" yaz
                } else {
                    label.setText(Integer.toString(current.data)); // Diğer durumlarda veriyi yaz
                }
                current = current.down; // Bir sonraki düğüme geç
            }
            panel.add(label); // Label'i panele ekle
        }
    }
    frame.pack(); // Yeniden boyutlandır
    frame.setVisible(true); // Frame'i görünür yap
}*/

    /* public void printList(JFrame frame) {
    NodeClass current = head;
    JPanel panel = new JPanel(new GridLayout(3, 9)); // 3 satır, 9 sütunlu bir grid oluştur
    for (int i = 0; i < 3; i++) { // Her satır için
        for (int j = 0; j < 9; j++) { // Her sütun için
            JLabel label = new JLabel(); // Yeni bir JLabel oluştur
            if (current != null) {
                if (current.data == -1) {
                    label.setText("X"); // -1 ise "X" yaz
                } else {
                    label.setText(Integer.toString(current.data)); // Diğer durumlarda veriyi yaz
                }
                current = current.down; // Bir sonraki düğüme geç
            }
            panel.add(label); // Label'i panele ekle
        }
    }
    frame.add(panel); // Paneli frame'e ekle
    frame.setVisible(true); // Frame'i görünür yap
}*/
     
     
   /* public void printList2(JFrame frame) {
    NodeClass current = head;
    
    int labelCount = GameWindow.getJPanel1().getComponentCount(); // jPanel1 içindeki bileşen sayısını al
    int index = 0; // JLabel dizisi için indeks
    
    // Her bir bileşen için
    for (int i = 0; i < labelCount; i++) {
        // Eğer bileşen bir JLabel ise
        if (jPanel1.getComponent(i) instanceof JLabel) {
            JLabel label = (JLabel) jPanel1.getComponent(i); // Bileşeni bir JLabel'e dönüştür
            // Eğer veri varsa yaz, yoksa "X" yaz
            if (current != null) {
                if (current.data == -1) {
                    label.setText("X");
                } else {
                    label.setText(Integer.toString(current.data));
                }
                current = current.down; // Bir sonraki düğüme geç
            } else {
                label.setText("X");
            }
            index++; // Bir sonraki JLabel için indeksi artır
        }
    }
    
    frame.setVisible(true); // Frame'i görünür yap
}
    */


    /*public void printList2(JFrame frame) {
    NodeClass current = head;
    
    PlayerCard pl = new PlayerCard();
    JPanel jPanel1 = pl.getJPanel1();
    jPanel1.removeAll(); // Önce mevcut tüm bileşenleri temizle

    // Yeni label'ları oluşturup panele ekle
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 9; j++) {
            JLabel label = new JLabel();
            if (current != null) {
                if (current.data == -1) {
                    label.setText("X");
                } else {
                    label.setText(Integer.toString(current.data));
                }
                current = current.down;
            }
            jPanel1.add(label);
        }
    }

    jPanel1.revalidate(); // Yeniden düzenleme yap
    jPanel1.repaint(); // Yeniden çiz
    frame.pack(); // Frame boyutunu ayarla
    frame.setVisible(true); // Frame'i görünür yap
}*/

    
    
    
    
    
    
    
    
    
  /*  
    public void printList(JFrame frame) {
    NodeClass current = head;
    JPanel panel = new JPanel(new GridLayout(0, 9)); // 9 sütunlu bir grid oluştur
    while (current != null) {
        JLabel label = new JLabel(); // Yeni bir JLabel oluştur
        if (current.data == -1) {
            label.setText("X"); // -1 ise "X" yaz
        } else {
            label.setText(Integer.toString(current.data)); // Diğer durumlarda veriyi yaz
        }
        panel.add(label); // Label'i panele ekle
        current = current.down; // Bir sonraki düğüme geç
        if (panel.getComponentCount() % 9 == 0) {
            // Her 9 labelden sonra bir satır sonu ekleyerek 9x3 görünümü sağla
            panel.add(Box.createHorizontalGlue());
        }
    }
    frame.add(panel); // Paneli frame'e ekle
    frame.setVisible(true); // Frame'i görünür yap
}*/

    
    
    
    
    
    
/*    public void printList(JFrame frame) {
    NodeClass current = head;
    JPanel panel = new JPanel(); // JPanel oluştur
    while (current != null) {
        JLabel label = new JLabel(); // Yeni bir JLabel oluştur
        if (current.data == -1) {
            label.setText("X "); // -1 ise "X" yaz
        } else {
            label.setText(current.data + " "); // Diğer durumlarda veriyi yaz
        }
        panel.add(label); // Label'i panele ekle
        current = current.down; // Bir sonraki düğüme geç
    }
    frame.add(panel); // Paneli frame'e ekle
    frame.setVisible(true); // Frame'i görünür yap
}*/


}