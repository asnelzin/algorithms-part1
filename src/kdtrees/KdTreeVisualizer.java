package kdtrees;

/*************************************************************************
 *  Compilation:  javac kdtrees.KdTreeVisualizer.java
 *  Execution:    java kdtrees.KdTreeVisualizer
 *  Dependencies: StdDraw.java Point2D.java kdtrees.KdTree.java
 *
 *  Add the points that the user clicks in the standard draw window
 *  to a kd-tree and draw the resulting kd-tree.
 *
 *************************************************************************/

public class KdTreeVisualizer {

    public static void main(String[] args) {
        StdDraw.show(0);
        KdTree kdtree = new KdTree();
        In in = new In(args[0]);
        while (!in.isEmpty()) {
                //double x = StdDraw.mouseX();
                //double y = StdDraw.mouseY();
            double x = in.readDouble();
            double y = in.readDouble();
            //System.out.printf("%8.6f %8.6f\n", x, y);
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }
        StdDraw.clear();
        kdtree.draw();
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLUE);
        Point2D query = new Point2D(.81, .30);
        query.draw();
        kdtree.nearest(query).draw();
        StdDraw.show(50);

    }
}
