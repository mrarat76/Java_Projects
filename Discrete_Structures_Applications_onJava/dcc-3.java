/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static int[] findOddNumbers(int[][] matrix) {
        List<Integer> oddNumbersList = new ArrayList<>();

        for (int[] row : matrix) {
            for (int num : row) {
                if (num % 2 != 0) {
                    oddNumbersList.add(num);
                }
            }
        }

        int[] oddNumbersArray = new int[oddNumbersList.size()];
        for (int i = 0; i < oddNumbersList.size(); i++) {
            oddNumbersArray[i] = oddNumbersList.get(i);
        }

        return oddNumbersArray;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {56, 23, 678, 231},
            {234, 21, 78, 26},
            {654, 33, 32, 67},
            {189, 35, 56, 89}
        };

        int[] oddNumbers = findOddNumbers(matrix);

        System.out.println("Odd Numbers in the Matrix: " + Arrays.toString(oddNumbers));
    }
}