/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mavenproject7;

/**
 *
 * @author Monster
 */
public class Mavenproject7 {

    public static void main(String[] args) {
       int x = 6;
        int y = 8;

        
        String binaryX = Integer.toBinaryString(x);
        String binaryY = Integer.toBinaryString(y);

        System.out.println("Binary representation of x (6): " + binaryX);
        System.out.println("Binary representation of y (8): " + binaryY);

       
        int andResult = x & y;
        System.out.println("Bitwise AND (x & y): " + andResult);

       
        int orResult = x | y;
        System.out.println("Bitwise OR (x | y): " + orResult);

   
        int xorResult = x ^ y;
        System.out.println("Bitwise XOR (x ^ y): " + xorResult);

       
        int notXResult = ~x;
        System.out.println("Bitwise NOT (~x): " + notXResult);

        
        int leftShiftResult = x << 2;
        System.out.println("Left shift (x << 2): " + leftShiftResult);

       
        int rightShiftResult = y >> 2;
        System.out.println("Right shift (y >> 2): " + rightShiftResult);
       
    }
}
