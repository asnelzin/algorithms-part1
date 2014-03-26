package kdtrees;

public class KdTree {
    private Node root;
    private int count;

    private static final boolean VERTICAL = true;

    private class Node {
        private Point2D point;
        private RectHV rect;
        private Node left, right;
        private boolean orient;

        public Node(Point2D point, RectHV rect, boolean orient) {
            this.point = point;
            this.rect = rect;
            this.orient = orient;
        }
    }

    private class NearestPoint {
        private Point2D point;
        public NearestPoint(Point2D point) {
            this.point = point;
        }
        public void set(Point2D point) {
            this.point = point;
        }
        public Point2D get() {
            return this.point;
        }
    }

    public KdTree() {
        count = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return count;
    }

    public void insert(Point2D point) {
        if (contains(point))
            return;
        if (isEmpty())
            root = new Node(point, new RectHV(0, 0, 1, 1), VERTICAL);
        else
            root = insert(root, null, point);
        count++;
    }

    private Node insert(Node x, Node parent, Point2D point) {
        if (x == null) {
            boolean isLess = (comparePoints(point, parent.point, parent.orient) < 0);
            return new Node(point, cut(parent, isLess), !parent.orient);
        }
        int cmp = comparePoints(point, x.point, x.orient);
        if (cmp < 0) x.left  = insert(x.left, x, point);
        else         x.right = insert(x.right, x, point);
        return x;
    }

    public boolean contains(Point2D point) {
        return contains(root, point);
    }

    private boolean contains(Node x, Point2D point) {
        if (x == null)             return false;
        if (x.point.equals(point)) return true;
        int cmp = comparePoints(point, x.point, x.orient);
        if (cmp < 0) return contains(x.left, point);
        else         return contains(x.right, point);

    }

    private int comparePoints(Point2D a, Point2D b, boolean orient) {
        if (orient == VERTICAL) {
            if (a.x() < b.x())      return -1;
            else if (a.x() > b.x()) return +1;
            else                    return 0;
        }
        else {
            if (a.y() < b.y())      return -1;
            else if (a.y() > b.y()) return +1;
            else                    return 0;
        }
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node x) {
        if (x == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        x.point.draw();
        drawLine(x);
        draw(x.left);
        draw(x.right);
    }

    private void drawLine(Node x) {
        StdDraw.setPenRadius(.003);
        if (x.orient == VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.point.x(), x.rect.ymin(), x.point.x(), x.rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rect.xmin(), x.point.y(), x.rect.xmax(), x.point.y());
        }
    }

    private RectHV cut(Node x, boolean isLess) {
        if (x.orient == VERTICAL)
            if (isLess)
                return new RectHV(x.rect.xmin(), x.rect.ymin(), x.point.x(), x.rect.ymax());
            else
                return new RectHV(x.point.x(), x.rect.ymin(), x.rect.xmax(), x.rect.ymax());
        else
            if (isLess)
                return new RectHV(x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.point.y());
            else
                return new RectHV(x.rect.xmin(), x.point.y(), x.rect.xmax(), x.rect.ymax());
    }

    public Iterable<Point2D> range(RectHV qrect) {
        Queue<Point2D> q = new Queue<Point2D>();
        if (!isEmpty())
            range(root, qrect, q);
        return q;
    }

    private void range(Node x, RectHV qrect, Queue<Point2D> q) {
        if (x == null) return;
        if (qrect.contains(x.point))
            q.enqueue(x.point);
        if (x.left != null && qrect.intersects(x.left.rect))
            range(x.left,  qrect, q);
        if (x.right != null && qrect.intersects(x.right.rect))
            range(x.right, qrect, q);
    }

    //private Point2D ans;

    public Point2D nearest(Point2D qpoint) {
        if (isEmpty())
            return null;
        NearestPoint ans = new NearestPoint(root.point);
        nearest(root, qpoint, ans);
        return ans.get();
    }

    private void nearest(Node x, Point2D qpoint, NearestPoint ans) {
        if (x == null) return;
        if (x.point.distanceSquaredTo(qpoint) < ans.get().distanceSquaredTo(qpoint))
            ans.set(x.point);

        int cmp = comparePoints(qpoint, x.point, x.orient);
        if (cmp < 0) {
            nearest(x.left, qpoint, ans);
            if (x.right != null) {
                if (x.right.rect.distanceSquaredTo(qpoint) < ans.get().distanceSquaredTo(qpoint))
                    nearest(x.right, qpoint, ans);
            }
        }
        else {
            nearest(x.right, qpoint, ans);
            if (x.left != null) {
                if (x.left.rect.distanceSquaredTo(qpoint) < ans.get().distanceSquaredTo(qpoint))
                    nearest(x.left, qpoint, ans);
            }
        }
    }
}