import java.util.Comparator;
import java.util.Arrays;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new slopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        double dy = that.y - this.y;
        double dx = that.x - this.x;    
        if (dx != 0)
            if (dy == 0)
                return +0;
            else
                return dy / dx;
        else {
            if (dy != 0)
                return Double.POSITIVE_INFINITY;
            else
                return Double.NEGATIVE_INFINITY; 
        }
    }

    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    private class slopeOrder implements Comparator<Point> {
        public int compare(Point v, Point w) {
            if (slopeTo(v) < slopeTo(w)) return -1;
            if (slopeTo(v) > slopeTo(w)) return +1;
            return 0;
        }
    } 

    // unit test
    public static void main(String[] args) {
        // StdDraw.setXscale(0, 50);
        // StdDraw.setYscale(0, 50);
        // StdDraw.setPenRadius(.002);


        // /* YOUR CODE HERE */
        // Point[] points = new Point[5];
        // points[0] = new Point(5, 20);
        // points[1] = new Point(10, 7);
        // points[2] = new Point(23, 27);
        // points[3] = new Point(31, 14);
        // points[4] = new Point(37, 6);

        // Point p = new Point(5, 20);
        // Arrays.sort(points, p.SLOPE_ORDER);
        // for (int i = 0; i < 5; i++) {
        //     points[i].draw();
        //     p.drawTo(points[i]);
        //     StdOut.println(points[i] + "    " + p.slopeTo(points[i]));
        // }
        // StdDraw.show();
        Point p = new Point(423, 327);
        Point q = new Point(423, 327);
        StdOut.println(p.slopeTo(q));

    }
}
