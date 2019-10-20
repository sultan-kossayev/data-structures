package datastructure.tree;

/**
 * The interface of the Binary Tree data structure
 *
 * @param <T> a type of elements that the tree contains
 */
public interface BinaryTree<T> extends Tree<T> {

    /**
     * Returns the left child of the given parent
     * or null if the parent doesn't have a left child
     *
     * @param parent a parent node a left child of which to return
     * @return left child or null
     */
    Node<T> leftOf(Node<T> parent);

    /**
     * Returns the right child of the given parent
     * or null if the parent doesn't have the right child
     *
     * @param parent a parent node a right child of which to return
     * @return right child or null
     */
    Node<T> rightOf(Node<T> parent);
}
