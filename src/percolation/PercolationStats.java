public class PercolationStats {
    private int T;
    private int N;
    private double[] results;
    private double mean;
    private double stddev;
    private double cLo;
    private double cHi;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new java.lang.IllegalArgumentException();
        else {
            this.N = N;
            this.T = T;
            results = new double[T];
            int counter = 0;

            for (int i = 0; i < T; i++) {
                Percolation p = new Percolation(N);
                while (!p.percolates()) {
                    int x = StdRandom.uniform(N) + 1;
                    int y = StdRandom.uniform(N) + 1;
                    if (!p.isOpen(x, y)) {
                        p.open(x, y);
                        counter++;
                    }
                }
                results[i] = counter / (double) (N*N);
                counter = 0;
            }
        }
    }

    public double mean() {
        if (mean == 0)
            mean = StdStats.mean(results); 
        return mean;
    }

    public double stddev() {
        if (stddev == 0)
            stddev = StdStats.stddev(results);
        return stddev;
    }

    public double confidenceLo() {
        if (cLo == 0)
            cLo = mean() - ((1.96 * stddev()) / Math.sqrt(T));
        return cLo;
    }

    public double confidenceHi() {
        if (cHi == 0)
            cHi = mean() + ((1.96 * stddev()) / Math.sqrt(T));
        return cHi;

    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(N, T);
        double mean = ps.mean();
        double stddev = ps.stddev();
        double cLo = ps.confidenceLo();
        double cHi = ps.confidenceHi();
        StdOut.println("mean                    = " + mean);
        StdOut.println("stddev                  = " + stddev);
        StdOut.println("95% confidence interval = " + cLo + ", " + cHi);
    }
} 