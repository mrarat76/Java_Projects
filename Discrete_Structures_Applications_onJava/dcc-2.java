/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
public class Main {
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transpose = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }

        return transpose;
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

        int[][] transpose = transposeMatrix(matrix);

        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[0].length; j++) {
                System.out.print(transpose[i][j] + " ");
            }
            System.out.println();
        }
    }
}

