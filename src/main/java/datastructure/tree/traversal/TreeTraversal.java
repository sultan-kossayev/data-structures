package datastructure.tree.traversal;

import datastructure.tree.Tree;

import java.util.Collection;

/**
 * The interface that defines traversal order of trees
 *
 * @param <T> a type of elements that the tree contains
 */
public interface TreeTraversal<T> {

    /**
     * Returns a collection of nodes in particular order.
     * The order is defined by the traversal implementation
     *
     * @param tree the tree to traverse
     * @return nodes in particular order or empty collection
     */
    Collection<T> traverse(Tree<T> tree);
}
