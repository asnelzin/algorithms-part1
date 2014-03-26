public class Tests {
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        int c = 0;
        while (c < 40) {
            int x = StdRandom.uniform(10) + 1;
            int y = StdRandom.uniform(10) + 1;
            if (!p.isOpen(x, y)) {
                p.open(x, y);
                c++;
            }
        }

        for (int i = 1; i <= 10; i++) {
            if (p.isFull(10, i))
                StdOut.println("(10, " + i + ") is Full");
            if (p.uf.connected(90 + i - 1, 100))
                StdOut.println("Allo");
        }

    }
}