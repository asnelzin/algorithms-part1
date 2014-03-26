import java.util.Random;
public class GraphicalTest {

    private int N;
    public Percolation p;

    public GraphicalTest(int N) {
        this.N = N;
        this.p = new Percolation(N);
        StdDraw.setXscale(0, N*50 + (N + 1)*5);
        StdDraw.setYscale(N*50 + (N + 1)*5, 0);
        StdDraw.clear(StdDraw.BLACK);
    }

    public void grid() {
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i = 1; i <= this.N; i++)
            for (int j = 1; j <= this.N; j++) {
                if (this.p.isFull(i, j)) {
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.filledSquare(30 + 55*(j - 1), 30 + 55*(i - 1), 25);
                    continue;
                }
                if (this.p.isOpen(i, j)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledSquare(30 + 55*(j - 1), 30 + 55*(i - 1), 25);
                    continue;
                }

                //StdDraw.setPenColor(StdDraw.WHITE);
                //StdDraw.square(30 + 55*(j - 1), 30 + 55*(i - 1), 25);
            }
        //StdDraw.filledSquare(62.5, 7.5, 25);
    }

    public static void main(String[] args) {
        int n = 4;
        int p = 0;
        GraphicalTest gt = new GraphicalTest(n);
        int counter = 0;
        while (counter < p) {
            int x = StdRandom.uniform(n) + 1;
            int y = StdRandom.uniform(n) + 1;
            if (!gt.p.isOpen(x, y)) {
                gt.p.open(x, y);
                counter++;
            }
        }
        gt.p.open(1, 4);
        gt.p.open(2, 4);
        gt.p.open(3, 4);
        gt.p.open(4, 4);
        gt.p.open(4, 1);
        gt.grid();
        StdOut.println(gt.p.percolates());
    }
}