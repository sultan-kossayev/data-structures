package datastructure.linkedlist;

/**
 * The implementation of the singly linked list
 */
public class SinglyLinkedList<T> implements LinkedList<T> {

    /**
     * The number of nodes the list contains
     */
    private int size;

    /**
     * The reference to the head (first element) of the list
     */
    private Node head;

    /**
     * The reference to the tail (last element) of the list
     */
    private Node tail;

    @Override
    public void addFirst(T element) {
        Node n = new Node(element);

        if (head == null) {
            tail = n;
        } else {
            n.next = head;
        }

        head = n;

        size++;
    }

    @Override
    public void addLast(T element) {
        Node n = new Node(element);

        if (tail == null) {
            head = n;
        } else {
            tail.next = n;
        }

        tail = n;

        size++;
    }

    @Override
    public T getFirst() {
        return head == null ? null : (T) head.value;
    }

    @Override
    public T getLast() {
        return tail == null ? null : (T) tail.value;
    }

    @Override
    public T removeFirst() {
        T value = null;
        if (head != null) {
            value = (T) head.value;
            head = head.next;
            tail = head == null ? null : tail;
            size--;
        }

        return value;
    }

    @Override
    public T removeLast() {
        throw new UnsupportedOperationException("This is operation is not supported. Use doubly linked list instead");
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
        T value;
        Node next;

        Node(T v) {
            this.value = v;
        }
    }
}
