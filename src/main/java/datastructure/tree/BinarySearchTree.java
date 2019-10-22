package datastructure.tree;

import datastructure.tree.traversal.TreeTraversal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The implementation of the Binary Search Tree using linked list
 */
public class BinarySearchTree<T extends Comparable> implements BinaryTree<T> {

    /**
     * The number of values that the tree contains
     */
    private int size;

    /**
     * The root of the tree
     */
    private LinkedNode<T> root;

    @Override
    public Node<T> leftOf(Node<T> parent) {
        return wrap(parent).left;
    }

    @Override
    public Node<T> rightOf(Node<T> parent) {
        return wrap(parent).right;
    }

    /**
     * Duplicates are not allowed.
     * If the value exists already in the tree, the reference to its node will be returned.
     */
    @Override
    public Node<T> add(T value) {
        LinkedNode added = add(root, value);
        if (root == null) {
            root = added;
        }

        size++;

        return added;
    }

    private LinkedNode<T> add(LinkedNode node, T value) {
        // a leaf node
        if (node == null) {
            return new LinkedNode(value, null, null);
        }

        LinkedNode added = node;
        // choose a subtree to insert a value
        int c = value.compareTo(node.value());
        if (c < 0) {
            // go left
            added = add(node.left, value);
            // link the added node only its parent, otherwise do nothing
            node.left = node.left == null? added : node.left;
        } else if (c > 0) {
            // go right
            added = add(node.right, value);
            // link the added node only its parent, otherwise do nothing
            node.right = node.right == null? added : node.right;
        }

        return added;
    }

    @Override
    public boolean remove(T value) {
        if (find(value) == null) {
            return false;
        }

        root = remove(root, value);
        size--;

        return true;
    }

    private LinkedNode remove(LinkedNode node, T value) {
        int c = value.compareTo(node.value());

        if (c > 0) {
            node.right = remove(node.right, value);
        } else if (c < 0) {
            node.left = remove(node.left, value);
        } else {
            // either right subtree exists or the node is a leaf
            if (node.left == null) {
                return node.right;
            }
            // only left subtree exists
            else if (node.right == null) {
                return node.left;
            }
            // has both left and right subtrees
            else {
                // find smallest in the right subtree
                LinkedNode smallest = node.right;
                while(smallest != null && smallest.left != null) {
                    smallest = smallest.left;
                }

                // swap the values
                node.value = smallest.value();

                node.right = remove(node.right, (T) smallest.value());
            }
        }

        return node;
    }

    @Override
    public Node<T> find(T value) {
        return find(root, value);
    }

    private LinkedNode<T> find(LinkedNode node, T value) {
        if (node == null) {
            return null;
        }

        int c = value.compareTo(node.value());
        if (c < 0) {
            return find(node.left, value);
        } else if (c > 0) {
            return find(node.right, value);
        }

        return node;
    }

    @Override
    public Node<T> root() {
        return root;
    }

    @Override
    public Collection<Node<T>> childrenOf(Node<T> parent) {
        List<Node<T>> children = new ArrayList<>();

        if (wrap(parent).left != null) {
            children.add(wrap(parent).left);
        }

        if (wrap(parent).right != null) {
            children.add(wrap(parent).right);
        }

        return children;
    }

    @Override
    public Collection<T> values(TreeTraversal traverseUsing) {
        return traverseUsing.traverse(this);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private LinkedNode wrap(Node node) {
        return (LinkedNode) node;
    }

    private static class LinkedNode<T extends Comparable> implements Node<T> {

        private LinkedNode<T> left;
        private LinkedNode<T> right;
        private T value;

        LinkedNode(T v, LinkedNode l, LinkedNode r) {
            value = v;
            left = l;
            right = r;
        }

        @Override
        public T value() {
            return value;
        }
    }
}
