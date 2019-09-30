import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final  double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final double mean;
    private final double stddev;

    public PercolationStats(int n, int trials) {      // perform trials on an grid_len-by-grid_len grid
        final double[] threshold;
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException("n or trials is less than or equal to 0");
        }
        threshold = new double[trials];
        this.trials = trials;
        int row, col;
        for (int i = 0; i < trials; i++) {
            // System.out.println("in trial");
            Percolation a = new Percolation(n);
            while (!a.percolates()) {
                // System.out.println("in one open");
                row = StdRandom.uniform(1, n+1);
                col = StdRandom.uniform(1, n+1);
                // System.out.println(row+":"+col);
                a.open(row, col);
            }
            threshold[i] = (a.numberOfOpenSites()/(n*n*1.0));
        }
        this.mean = StdStats.mean(threshold);
        this.stddev = StdStats.stddev(threshold);

    }

    public double mean() {         // sample mean of percolation threshold
        return mean;
    }

    public double stddev() {        // sample standard deviation of percolation threshold
        return stddev;
    }

    public double confidenceLo() {       // low  endpoint of 95% confidence interval
        return mean - (CONFIDENCE_95*stddev)/Math.sqrt(trials);
    }

    public double confidenceHi() {        // high endpoint of 95% confidence interval
        return mean + (CONFIDENCE_95*stddev)/Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats a = new PercolationStats(n, t);
        System.out.print("mean                    = ");
        System.out.println(a.mean);
        System.out.print("stddev                  = ");
        System.out.println(a.stddev);
        System.out.print("95% confidence interval = [");
        System.out.print(a.confidenceLo());
        System.out.print(", ");
        System.out.print(a.confidenceHi());
        System.out.println("]");


    }      // test client (described below)
}