package kdtrees;

public class Generator {

    public static void main(String[] args) {
//        int N = Integer.parseInt(args[0]);
//
//        for (int i = 0; i < N; i++) {
//            double x = Math.random();
//            double y = Math.random();
//            StdOut.printf("%.6f %.6f\n", x, y);
//        }
        StdDraw.show(0);
        KdTree kdtree = new KdTree();
        boolean newPoint = true;
        while (true) {
            if (StdDraw.mousePressed() && newPoint) {
                newPoint = false;
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                StdOut.printf("%8.6f %8.6f\n", x, y);
                Point2D p = new Point2D(x, y);
                kdtree.insert(p);
                StdDraw.clear();
                kdtree.draw();
            }
            if (!StdDraw.mousePressed()) newPoint = true;
            StdDraw.show(50);
        }
    }
}