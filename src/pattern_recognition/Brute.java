import java.util.Arrays;

public class Brute {
    public static  void main(String[] args) {
        int[] values = In.readInts(args[0]);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(.008);

        int n = values[0];
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(values[2*i + 1], values[2*i + 2]);
            points[i].draw();
        }
        StdDraw.setPenRadius(.004);
        Arrays.sort(points);

        for (int p = 0; p < n; p++)
            for (int q = p + 1; q < n; q++)
                for (int r = q + 1; r < n; r++)
                    for (int s = r + 1; s < n; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) && 
                            points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
                            StdOut.println(points[p] + " -> " + points[q] + " -> " + points[r] + " -> " + points[s]);
                            points[p].drawTo(points[s]);
                        }
                    }

    }
}