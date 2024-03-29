/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastrct;

/**
 *
 * @author mehdiarat
 */
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TombolaGame {
    
    // 9X3 kart yapılacak.
    // Bu kartları guile
    // Kartlara random kütüphanesinden gelsin
    // 
    
    
     MultiLinkedList playerCards; // Multi-linked list for storing player cards
    int numPlayers;

    public TombolaGame(int numPlayers) {
        this.numPlayers = numPlayers;
        playerCards = new MultiLinkedList();

        // Generate random cards for each player
        for (int i = 0; i < numPlayers; i++) {
            MultiLinkedList card = generateCard();
            playerCards.appendList(card);
        }
    }

    // Method to generate a random card for a player
    private MultiLinkedList generateCard() {
        Random random = new Random();
        MultiLinkedList card = new MultiLinkedList();
        Set<Integer> selectedNumbers = new HashSet<>();//double linked list ile yap

        // Generate 15 unique random numbers for the card
        while (selectedNumbers.size() < 15) {
            int randomNumber = random.nextInt(90) + 1;
            if (!selectedNumbers.contains(randomNumber)) {
                card.add(randomNumber);
                selectedNumbers.add(randomNumber);
            }
        }
        
        
         int numX = 5; // Number of nodes to mark as X
        while (numX > 0) {
            int index = random.nextInt(15);
            NodeClass current = card.head;
            while (index > 0) {
                current = current.down;
                index--;
            }
            if (current.data != -1) {
                current.data = -1;
                numX--;
            }
        }

        return card;
        
    }

    // Method to simulate calling out a number and updating cards
    public void callNumber(int number) {
        playerCards.remove(number); // Remove the called number from all player's cards
    }

    // Method to check if any player has won
    public boolean checkWin() {
        return playerCards.isWinningCard();
    }

    // Method to print the current version of each player's card
    public void printCards() {
        System.out.println("Players' cards:");
        playerCards.printList();
    }
}


    
    // Assume MultiLinkedList and Node classes are defined above
/*
    MultiLinkedList player1Card = new MultiLinkedList();
    MultiLinkedList player2Card = new MultiLinkedList();

    // Utility to generate a random permutation of numbers from 1 to 90
    public List<Integer> generatePermutation(int n) {
        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    // Utility to print the card
    public void printCard(MultiLinkedList card) {
        Node current = card.head;
        int count = 0;
        while (current != null) {
            if (current.number == -1) {
                System.out.print("X ");
            } else {
                System.out.print(current.number + " ");
            }
            count++;
            if (count % 9 == 0) { // Assuming 9 numbers per row
                System.out.println();
            }
            current = current.next;
        }
        System.out.println();
    }

    // Utility to check for a winning condition (i.e., a full row)
    public boolean checkForWin(MultiLinkedList card) {
        // Implement logic to check for a win based on your game rules
        // This method should check for five marked numbers in any row.
        // Return true if there is a win; otherwise, return false.
        // ...
        return false; // Placeholder for compilation
    }

    // Utility to mark a number on the player's card
    public void markNumber(MultiLinkedList card, int number) {
        if (card.markNumber(number)) {
            System.out.println("Number " + number + " marked.");
        }
    }

    public void playGame() {
        List<Integer> randomNumbers = generatePermutation(90);

        // Initialize cards for both players
        // Populate player1Card and player2Card here...

        // Gameplay loop
        for (int number : randomNumbers) {
            System.out.println("Number drawn: " + number);

            // Players mark the number if it's on their card
            markNumber(player1Card, number);
            markNumber(player2Card, number);

            // Check for win after each number is marked
            if (checkForWin(player1Card)) {
                System.out.println("Player 1 wins!");
                printCard(player1Card);
                break;
            }

            if (checkForWin(player2Card)) {
                System.out.println("Player 2 wins!");
                printCard(player2Card);
                break;
            }

            // Print the current state of each card
            System.out.println("Player 1's card:");
            printCard(player1Card);
            System.out.println("Player 2's card:");
            printCard(player2Card);
        }
    }

    public static void main(String[] args) {
        TombolaGame game = new TombolaGame();
        game.playGame();
    }*/


