package datastructure.queue;

/**
 * The linked list based implementation of the queue
 *
 * @param <T> elements the queue holds
 */
public class LinkedQueue<T> implements Queue<T> {

    /**
     * The number of elements the queue holds
     */
    private int size;

    /**
     * The pointer to the first element of the queue
     */
    private Node<T> first;

    /**
     * The pointer to the last element of the queue
     */
    private Node<T> last;

    @Override
    public T first() {
        return first == null ? null : first.val;
    }

    @Override
    public T dequeue() {
        if (first == null) {
            return null;
        }

        T val = first.val;
        first = first.next;

        if (first == null) { // releasing the reference for GC
            last = null;
        }

        size--;

        return val;
    }

    @Override
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }

        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<T> {
        T val;
        Node<T> next;

        Node(T val) {
            this.val = val;
        }
    }
}
