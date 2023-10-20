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
public class NewClass {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();

        // Check if the number is divisible by 4 and 6
        boolean divisibleBy4And6 = (number % 4 == 0) && (number % 6 == 0);

        // Check if the number is divisible by 4 or 6
        boolean divisibleBy4Or6 = (number % 4 == 0) || (number % 6 == 0);

        // Check if the number is divisible by 5 or 6, but not both
        boolean divisibleBy5XOR6 = ((number % 5 == 0) || (number % 6 == 0)) && !(number % 5 == 0 && number % 6 == 0);

        // Display the results
        System.out.println(number + " is divisible by 4 and 6: " + divisibleBy4And6);
        System.out.println(number + " is divisible by 4 or 6: " + divisibleBy4Or6);
        System.out.println(number + " is divisible by 5 or 6, but not both: " + divisibleBy5XOR6);

        scanner.close();
    }
        

}
