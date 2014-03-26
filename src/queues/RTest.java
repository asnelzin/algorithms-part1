public class RTest {
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(0);
        rq.enqueue(4);
        while (!rq.isEmpty())
            StdOut.println(rq.dequeue());
    }
}