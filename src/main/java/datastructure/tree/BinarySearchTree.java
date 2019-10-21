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
        return null;
    }

    @Override
    public Node<T> rightOf(Node<T> parent) {
        return null;
    }

    /**
     * Duplicates aren't allowed
     */
    @Override
    public Node<T> add(T value) {
        LinkedNode added;
        if (root == null) {
            root = added = new LinkedNode(value, null, null, null);
        } else {
            LinkedNode parent = root;
            LinkedNode curr = root;
            while(curr != null) {
                parent = curr;
                if (value.compareTo(curr.value()) < 0) {
                    curr = curr.left;
                } else if (value.compareTo(curr.value()) > 0) {
                    curr = curr.right;
                } else {
                    throw new IllegalArgumentException("The value already exists in the tree");
                }
            }

            added = new LinkedNode(value, parent, null, null);
            if (value.compareTo(parent.value()) < 0) {
                parent.left = added;
            } else {
                parent.right = added;
            }
        }

        size++;

        return added;
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public Node<T> find(T value) {
        LinkedNode found = null;

        LinkedNode curr = root;
        while(curr != null) {
            if (value.compareTo(curr.value()) < 0) {
                curr = curr.left;
            } else if (value.compareTo(curr.value()) > 0){
                curr = curr.right;
            } else {
                found = curr;
                break;
            }
        }

        return found;
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

        private LinkedNode<T> parent;
        private LinkedNode<T> left;
        private LinkedNode<T> right;
        private T value;

        LinkedNode(T v, LinkedNode p, LinkedNode l, LinkedNode r) {
            value = v;
            parent = p;
            left = l;
            right = r;
        }

        @Override
        public T value() {
            return value;
        }
    }
}
