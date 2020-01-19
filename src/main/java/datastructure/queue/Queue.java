package datastructure.queue;

import datastructure.DataStructure;

/**
 * Interface of the queue data structure
 *
 * todo: make the queue iterable
 * @param <T>
 */
public interface Queue<T> extends DataStructure {

    /**
     * Returns but doesn't removes the first element of the queue
     * @return first element or null if the queue is empty
     */
    T first();

    /**
     * Removes and returns the first element of the queue
     * @return first element or null if the queue is empty
     */
    T dequeue();

    /**
     * Inserts the element to the end of the queue
     * @param element to be inserted
     */
    void enqueue(T element);
}
