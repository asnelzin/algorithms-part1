public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int counter = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
            queue.enqueue(StdIn.readString());
        for (String s : queue)
            if (counter < k) {
                StdOut.println(queue.dequeue());
                counter++;
            }
            else
                break;
    }
}