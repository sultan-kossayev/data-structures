package datastructure.tree;

import datastructure.DataStructure;

/**
 * The interface of the Tree data structure
 *
 * @param <T> a type of elements that the tree contains
 */
public interface Tree<T> extends DataStructure {

    /**
     * Adds an element to the tree.
     * Insertion rules depends on a tree implementation.
     *
     * @param value a value to add to the tree
     * @return a reference to the node that contains the added value
     */
    Node<T> add(T value);

    /**
     * Removes a value from the tree.
     * If an implementation allows duplicates then the first found value must be removed.
     *
     * @param value a value to remove
     * @return true if the value was removed, otherwise false
     */
    boolean remove(T value);

    /**
     * Finds a value from the tree.
     * If an implementation allows duplicates then the first node that contains the value must be returned.
     *
     * @param value a value to find
     * @return a reference to the node that contains the value
     */
    Node<T> find(T value);

    /**
     * The interface of a node tree
     */
    interface Node<T> {

        T element();
    }
}