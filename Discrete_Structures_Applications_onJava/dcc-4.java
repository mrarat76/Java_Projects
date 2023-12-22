/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
public class Main {
    public static int findSmallestInColumnOfLargest(int[][] matrix) {
        int largest = matrix[0][0];
        int largestRow = 0;
        int largestCol = 0;

        // Find the largest element and its position
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > largest) {
                    largest = matrix[i][j];
                    largestRow = i;
                    largestCol = j;
                }
            }
        }

        // Find the smallest element in the same column
        int smallest = matrix[0][largestCol];
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][largestCol] < smallest) {
                smallest = matrix[i][largestCol];
            }
        }

        return smallest;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {56, 23, 678, 231},
            {234, 21, 78, 26},
            {654, 33, 22, 67},
            {189, 35, 56, 89}
        };

        int smallestInColumnOfLargest = findSmallestInColumnOfLargest(matrix);

        System.out.println("Smallest element in the column containing the largest element: " + smallestInColumnOfLargest);
    }
}
