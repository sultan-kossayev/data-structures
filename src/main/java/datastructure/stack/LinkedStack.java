package datastructure.stack;

/**
 * Stack implementation based on linked list
 * @param <T> elements the stack holds
 */
public class LinkedStack<T> implements Stack<T> {

    /**
     * Number of elements the stack holds
     */
    private int size;

    /**
     * The pointer to the top of the stack
     */
    private Node<T> top;

    @Override
    public T top() {
        return top == null ? null : top.val;
    }

    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;

        size++;
    }

    @Override
    public T pop() {
        if (top == null) {
            return null;
        }

        T topVal = top.val;
        top = top.next;

        size--;

        return topVal;
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

        Node (T v) {
            val = v;
        }
    }
}
