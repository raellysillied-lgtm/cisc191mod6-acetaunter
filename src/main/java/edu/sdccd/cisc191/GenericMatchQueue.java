package edu.sdccd.cisc191;

import java.util.LinkedList;

public class GenericMatchQueue<T> {

    private final LinkedList<T> items = new LinkedList<>();

    public void enqueue(T item) {
         // TODO: add the item to the back of the queue
        items.addLast(item);
    }

    public T dequeue() {
        // TODO: remove and return the front item
        // throw IllegalStateException if the queue is empty
        if (items.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return items.removeFirst(); // I like the use of this return method
    }

    public T peek() {
         // TODO: return the front item without removing it
        // throw IllegalStateException if the queue is empty
        if (items.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return items.getFirst();
    }

    public boolean isEmpty() {
         // TODO: return true when the queue has no items
        return items.isEmpty(); // I find this also very effective, not much to say about this file, everything seems to be working great
    }

    public int size() {
        return items.size();
    }
}
