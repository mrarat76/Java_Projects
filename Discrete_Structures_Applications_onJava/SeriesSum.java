public class SeriesSum {

    public static double m(int i) {
        if (i == 1) {
            return 1.0 / 2; 
        } else {
            return m(i - 1) + (double) i / (i + 1);
        }
    }

    public static void main(String[] args) {
        int term = 3; 
        System.out.println("m(" + term + ") = " + m(term));
    }
}
