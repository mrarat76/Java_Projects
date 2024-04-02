/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastrct;

import javax.swing.JLabel;

/**
 *
 * @author mehdiarat
 */
public class NodeClass {
    int data;
    NodeClass nextcard;
    NodeClass next;
    NodeClass down;
    JLabel labe;

    public NodeClass(int data) {
        this.data = data;
        this.nextcard = null;
        this.next = null;
      
    }
    
    public NodeClass(JLabel labe){
        this.labe=labe;
        this.next=null;
    }
    
}
