/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject7;

import java.util.Scanner;

/**
 *
 * @author Monster
 */
public class NewClass1 {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a three-digit integer: ");
        int number = scanner.nextInt();

      
        if (number < 100 || number > 999) {
            System.out.println("Please enter a valid three-digit integer.");
        } else {
           
            int originalNumber = number;
            int digit1 = number % 10;
            number /= 10;
            int digit2 = number % 10;
            number /= 10;
            int digit3 = number;

           
            if (digit1 == digit3) {
                System.out.println(originalNumber + " is a palindrome number.");
            } else {
                System.out.println(originalNumber + " is not a palindrome number.");
            }
        }

        scanner.close();
    }
    
}
