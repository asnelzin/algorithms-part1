package kdtrees;

/**
 * Created with IntelliJ IDEA.
 * User: asnelzin
 * Date: 17.10.13
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public class KdTreeSpeedTest {
    public static void main(String[] args) {
        RectHV rect = new RectHV(.5, 0, .5, 1);
        StdOut.println(rect.intersects(new RectHV(0, 0, 1, 1)));
        String filename = args[0];
        In in = new In(filename);
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }

//        while (!StdIn.isEmpty()) {
//            double x = in.readDouble();
//            double y = in.readDouble();
//            Point2D p = new Point2D(x, y);
//            StdOut.println(kdtree.nearest(p));
//        }


    }
}
