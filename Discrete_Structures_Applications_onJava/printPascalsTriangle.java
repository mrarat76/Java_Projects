import java.util.Scanner;

public class printPascalsTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***Enter the number of rows for Pascal Triangle");
        int numRows = scanner.nextInt();
        printPascalsTriangle(numRows);
    }
    
    private static void printPascalsTriangle(int numRows) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                System.out.print(binomialCoefficient(i, j) + " ");
            }
            System.out.println();
        }
    }
    
    private static long binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        } else {
            return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
        }
    }
}
