/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastrct;

/**
 *
 * @author mehdiarat
 */
public class NodeClass {
    int data;
    NodeClass next;
    NodeClass down;

    public NodeClass(int data) {
        this.data = data;
        this.next = null;
        this.down = null;
    }
}
