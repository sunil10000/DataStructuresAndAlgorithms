import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> str = new RandomizedQueue<>();
        int i;
        for (i = 0; i < k; i++) {
            str.enqueue(StdIn.readString());
        }
        i++;
        String a;
        int j;
        while (!StdIn.isEmpty()) {
            a = StdIn.readString();
            j = StdRandom.uniform(0, i);
            if (j < k) {
                str.dequeue();
                str.enqueue(a);
            }
            i++;
        }
        for (String s:str) {
            System.out.println(s);
        }
    }
}
