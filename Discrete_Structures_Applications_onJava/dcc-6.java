/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
public class Main {
    public static int[][] booleanProduct(int[][] A, int[][] B) {
        int numRowsA = A.length;
        int numColsA = A[0].length;
        int numRowsB = B.length;
        int numColsB = B[0].length;

        if (numRowsA != numRowsB || numColsA != numColsB) {
            throw new IllegalArgumentException("Matrix dimensions must be the same for Boolean product.");
        }

        int[][] result = new int[numRowsA][numColsA];

        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsA; j++) {
                result[i][j] = A[i][j] * B[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] A = {
            {1, 0, 0, 1},
            {0, 1, 0, 1},
            {1, 1, 1, 1}
        };

        int[][] B = {
            {1, 0, 0, 1},
            {0, 1, 0, 1},
            {1, 1, 1, 1}
        };

        int[][] booleanProductResult = booleanProduct(A, B);

        // Print the Boolean product matrix
        for (int i = 0; i < booleanProductResult.length; i++) {
            for (int j = 0; j < booleanProductResult[0].length; j++) {
                System.out.print(booleanProductResult[i][j] + " ");
            }
            System.out.println();
        }
    }
}
