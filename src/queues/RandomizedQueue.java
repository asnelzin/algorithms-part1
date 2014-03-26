import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] stack;
    private int head = 0;

    public RandomizedQueue() {
        stack = (Item[]) new Object[1]; 
    }

    public boolean isEmpty() {
        return head == 0;
    }

    public int size() {
        return head;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (head == stack.length)
            resize(2 * stack.length);
        stack[head++] = item;

    }

    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int randIndex = StdRandom.uniform(head);
        Item item = stack[randIndex];
        stack[randIndex] = stack[--head];
        stack[head] = null;
        if (head > 0 && head == stack.length / 4)
            resize(stack.length / 2);
        return item;

    }

    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        return stack[StdRandom.uniform(head)];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < head; i++)
            copy[i] = stack[i];
        stack = copy;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] iterStack;
        private int iterHead;

        public RandomizedQueueIterator() {
            iterStack = (Item[]) new Object[head];
            for (int i = 0; i < head; i++)
                iterStack[i] = stack[i];
            iterHead = head;
        }

        public boolean hasNext() {
            return iterHead > 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (iterHead == 0)
                throw new java.util.NoSuchElementException();
            int randIndex = StdRandom.uniform(iterHead);
            Item item = iterStack[randIndex];
            iterStack[randIndex] = iterStack[--iterHead];
            iterStack[iterHead] = null;
            return item;
        }

    }
}