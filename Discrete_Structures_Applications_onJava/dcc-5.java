/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
public class Main {
    public static int findSumOfLargest2x2(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {
                int sum = matrix[i][j] + matrix[i][j + 1] + matrix[i + 1][j] + matrix[i + 1][j + 1];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {0, 9, 8, 7, 5, 8},
            {4, 2, 5, 3, 9, 6},
            {2, 8, 6, 4, 7, 1},
            {8, 7, 9, 7, 7, 0},
            {2, 7, 8, 6, 3, 9},
            {1, 6, 5, 4, 7, 6}
        };

        int sumOfLargest2x2 = findSumOfLargest2x2(matrix);

        System.out.println("Sum of the largest 2x2 matrix: " + sumOfLargest2x2);
    }
}
