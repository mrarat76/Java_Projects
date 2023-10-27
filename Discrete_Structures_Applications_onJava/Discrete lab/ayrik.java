/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Monster
 */
public class ayrik{

    // Check if x > 2 for all elements in the array
    public static boolean forAllX(int[] arr) {
        for (int x : arr) {
            if (x <= 2) {
                return false;
            }
        }
        return true;
    }

    // Check if there exists an element in the array such that x > 2
    public static boolean existsX(int[] arr) {
        for (int x : arr) {
            if (x > 2) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 5};

        boolean allSatisfy = forAllX(arr);
        boolean existsSatisfy = existsX(arr);

        System.out.println("For all x, x > 2: " + allSatisfy);
        System.out.println("Exists x such that x > 2: " + existsSatisfy);
    }
}

