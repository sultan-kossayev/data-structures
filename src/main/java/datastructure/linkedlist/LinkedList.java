package datastructure.linkedlist;

import datastructure.DataStructure;

/**
 * Interface of linked list data structure
 */
public interface LinkedList<T> extends DataStructure {

    /**
     * Adds an element to the beginning of the linked list
     */
    void addFirst(T element);

    /**
     * Adds an element to the end of the linked list
     */
    void addLast(T element);

    /**
     * Returns the first element of the list or null
     */
    T getFirst();

    /**
     * Returns the last element of the list or null
     */
    T getLast();

    /**
     * Removes the first element of the list or
     * if the list is empty does nothing
     */
    T removeFirst();

    /**
     * Removes the last element of the list or
     * if the list is empty does nothing
     */
    T removeLast();
}
