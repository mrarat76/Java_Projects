import java.util.Scanner;

public class PermutationsCombinations {

    public static long factorial(int number) {
        long result = 1;
        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }
        return result;
    }

    public static long permutations(int n, int r) {
        return factorial(n) / factorial(n - r);
    }

   
    public static long combinations(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        System.out.print("Enter the value of r: ");
        int r = scanner.nextInt();

        if (n >= r && n >= 0 && r >= 0) {
            System.out.println("Permutations P(" + n + ", " + r + ") = " + permutations(n, r));
            System.out.println("Combinations C(" + n + ", " + r + ") = " + combinations(n, r));
        } else {
            System.out.println("Please enter a nonnegative integer r that does not exceed n.");
        }
        scanner.close();
    }
}
