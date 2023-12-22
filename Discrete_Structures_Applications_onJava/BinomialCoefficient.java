import java.util.Scanner;

public class BinomialCoefficient {

    // Method to calculate factorial
    public static long factorial(int number) {
        long result = 1;
        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }
        return result;
    }

    // Method to calculate binomial coefficient C(n, k) = n! / (k! * (n-k)!)
    public static long binomialCoefficient(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    // Method to calculate the coefficient of x^(n-s)*y^s in (ax+by)^n
    public static long findCoefficient(int n, int a, int b, int s) {
        return binomialCoefficient(n, s) * (long)Math.pow(a, n - s) * (long)Math.pow(b, s);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        
        System.out.print("Enter the value of a: ");
        int a = scanner.nextInt();
        
        System.out.print("Enter the value of b: ");
        int b = scanner.nextInt();
        
        System.out.print("Enter the value of s: ");
        int s = scanner.nextInt();

        if (n >= 0 && s >= 0 && s <= n) {
            long coefficient = findCoefficient(n, a, b, s);
            System.out.println("The coefficient of x^" + (n-s) + "y^" + s + " in (ax+by)^n is: " + coefficient);
        } else {
            System.out.println("Please enter nonnegative integers for n and s, with s less than or equal to n.");
        }
        
        scanner.close();
    }
}
