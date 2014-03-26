package kdtrees;

public class PointSET {
    private SET<Point2D> set;
    public PointSET() {
        set = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void insert(Point2D p) {
        set.add(p);
    }

    public boolean contains(Point2D p) {
        return set.contains(p);
    }

    public void draw() {
        for (Point2D p : set)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> q = new Queue<Point2D>();
        for (Point2D p : set)
            if (rect.contains(p))
                q.enqueue(p);
        return q;
    }

    public Point2D nearest(Point2D p) {
        double dist, minDist = Double.POSITIVE_INFINITY;
        Point2D ans = null;

        for (Point2D q : set) {
            dist = q.distanceSquaredTo(p);
            if (dist < minDist) {
                minDist = dist;
                ans = q;
            }
        }
        return ans;
    }
}
