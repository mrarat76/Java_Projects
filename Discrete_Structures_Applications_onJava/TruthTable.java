public class TruthTable {
    public static void main(String[] args) {
        
        boolean[] values = { true, false };

       
        System.out.println("P\tQ\tP AND Q\tP OR Q\tP XOR Q\tNOT P\tP <=> Q\tP => Q\tNOT Q");

       
        for (boolean p : values) {
            for (boolean q : values) {
                boolean pAndQ = p && q;
                boolean pOrQ = p || q;
                boolean pXorQ = p ^ q;
                boolean notP = !p;
                boolean iff = p == q;  // P Iff Q
                boolean then = !p || q; // P => Q
                boolean notQ = !q;

                System.out.println(p + "\t" + q + "\t" + pAndQ + "\t" + pOrQ + "\t" + pXorQ + "\t" + notP + "\t" + iff + "\t" + then + "\t" + notQ);
            }
        }
    }
}
