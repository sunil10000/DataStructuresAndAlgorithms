import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] list;
    private int size;
    private int arraySize;

    public RandomizedQueue() {
        list = (Item[]) new Object[0];
        size = 0;
        arraySize = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item shouldn't be null");

        if (arraySize == 0) {
            list = (Item[]) new Object[1];
            arraySize++;
        }
        if (size == arraySize) {
            Item[] oldList = list;
            list = (Item[]) new Object[2*arraySize];
            for (int i = 0; i < size; i++) {
                list[i] = oldList[i];
            }
            arraySize = 2*arraySize;
        }
        list[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        int j = StdRandom.uniform(0, size);
        Item item = list[j];
        list[j] = list[size-1];
        list[size-1] = null;
        size--;

        if (size < arraySize/4) {
            Item[] oldList = list;
            list = (Item[]) new Object[arraySize/2];
            for (int i = 0; i < size; i++) {
                list[i] = oldList[i];
            }
            arraySize = arraySize/2;
        }

        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        int j = StdRandom.uniform(0, size);
        return list[j];
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private final int[] temp;
        private int i;
        private final int tempSize;

        public ListIterator() {
            temp = new int[size];
            for (int k = 0; k < size; k++) {
                temp[k] = k;
            }
            StdRandom.shuffle(temp);
            i = -1;
            tempSize = size;
        }

        public boolean hasNext() {
            return i < tempSize-1;
        }
        public Item next() {
            if (i > tempSize-2) throw new NoSuchElementException("RandomizedQueue underflow");
            return list[temp[++i]];
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> a = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            a.enqueue(i);
            System.out.println(a.size());
            System.out.println(a.arraySize);
        }
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println("hi");
        for (int  i: a) {
            System.out.println(i);
        }
        System.out.println("hi");
        System.out.println(a.size());
        System.out.println(a.arraySize);
    }
}
