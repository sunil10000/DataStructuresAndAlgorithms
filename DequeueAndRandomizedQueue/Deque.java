import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
     Node<Item> first;
     Node<Item> last;
     int size;

    private static class Node<Item> {
         Item item;
         Node<Item> next;
         Node<Item> prev;
    }

    public Deque() { // construct an empty deque
        first = null;
        size = 0;
        last = null;

    }

    public boolean isEmpty() {  // is the deque empty?
        return size == 0;
    }

    public int size() { // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {   // add the item to the front
        if (item == null) throw new IllegalArgumentException("item shouldn't be null");
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        }
        else {
            oldFirst.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {    // add the item to the end
        if (item == null) throw new IllegalArgumentException("item shouldn't be null");
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeFirst() { // remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;
        Node<Item> oldfirst = first;
        first = first.next;
        oldfirst = null;
        size--;
        if (!isEmpty()) {
            first.prev = null;
        }
        else {
            last = null;
        }
        return item;
    }

    public Item removeLast()  {  // remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = last.item;
        Node<Item> oldLast = last;
        last = last.prev;
        oldLast = null;
        size--;
        if (!isEmpty()) {
            last.next = null;
        }
        else {
            first = null;
        }
        return item;
    }

    public Iterator<Item> iterator() {  // return an iterator over items in order from front to end
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext() { return current!= null; }
        public Item next() {
            if (!hasNext())  throw new NoSuchElementException("Deque underflow");
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        deque.removeFirst();
        deque.removeLast();
        for (int i:deque) StdOut.println(i);
        StdOut.println(deque.size);
        StdOut.println(deque.first);
        StdOut.println(deque.last);
    }
}