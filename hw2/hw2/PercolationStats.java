package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        int[] statsSitesNum = new int[T];
        for (int i = 0; i < T; i++) {
            Percolation mypf = pf.make(N);
            while (!mypf.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                mypf.open(row, col);
            }
            statsSitesNum[i] = mypf.numberOfOpenSites();
        }

        int totalSites = N * N;
        double averageOpenSites = StdStats.mean(statsSitesNum);
        this.mean = averageOpenSites / totalSites;

        this.stddev = StdStats.stddev(statsSitesNum) / totalSites;

        final double factor = 1.96;
        double halfRange = factor * stddev / Math.sqrt(T);
        this.confidenceLow = this.mean - halfRange;
        this.confidenceHigh = this.mean + halfRange;

    }   // perform T independent experiments on an N-by-N grid
    public double mean() {
        return mean;
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return stddev;
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return confidenceLow;
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confidenceHigh;
    }                                // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        Stopwatch timetest = new Stopwatch();
        PercolationFactory pf = new PercolationFactory();
        PercolationStats myStats = new PercolationStats(100, 100, pf);
        System.out.println("runtime: " + timetest.elapsedTime());
        System.out.println("mean: " + myStats.mean());
        System.out.println("stddev: " + myStats.stddev());
        System.out.println("confidenceLow: " + myStats.confidenceLow());
        System.out.println("confidenceHigh: " + myStats.confidenceHigh());
    }
}

