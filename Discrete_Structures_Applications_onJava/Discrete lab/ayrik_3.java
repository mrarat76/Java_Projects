/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Monster
 */
public class ayrik_3 {
    public static boolean forAllX(int[] arr) {
    // Iterate through the array
    for (int x : arr) {
        // Check the condition x % 6 > 2
        if (!(x % 6 > 2)) {
            // If the condition is not met for any element, return false
            return false;
        }
    }
    // If the condition is met for all elements, return true
    return true;
}

public static void main(String[] args) {
    int[] array = {0, 5, 7, 8, 9, 13};
    boolean result = forAllX(array);
    
    if (result) {
        System.out.println("∀xP(x) is true for the given array.");
    } else {
        System.out.println("∀xP(x) is false for the given array.");
    }
}

}
