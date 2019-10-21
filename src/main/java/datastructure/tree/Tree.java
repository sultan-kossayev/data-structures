package datastructure.tree;

import datastructure.DataStructure;
import datastructure.tree.traversal.TreeTraversal;

import java.util.Collection;

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
     * @return a reference to the node that contains the value or null
     */
    Node<T> find(T value);

    /**
     * Returns the root of the tree.
     *
     * @return the root of the tree or null
     */
    Node<T> root();

    /**
     * Returns direct children of the given parent.
     *
     * @param parent a node children of which have to returned
     * @return a collection of children or empty
     */
    Collection<Node<T>> childrenOf(Node<T> parent);

    /**
     * Returns the values that the tree contains.
     * The ordering of the values depends on used traversal method.
     *
     * @return values that the tree contains
     */
    Collection<T> values(TreeTraversal traverseUsing);

    /**
     * The interface of a node tree
     */
    interface Node<T> {

        T value();
    }
}