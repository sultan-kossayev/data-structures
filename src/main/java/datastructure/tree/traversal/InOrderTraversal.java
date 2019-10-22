package datastructure.tree.traversal;

import datastructure.tree.BinaryTree;
import datastructure.tree.Tree;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Traverses the binary tree using in-order traversal
 */
public class InOrderTraversal<T> implements TreeTraversal<T> {

    /**
     * @throws IllegalArgumentException if the tree doesn't implement {@link BinaryTree}
     */
    @Override
    public Collection<T> traverse(Tree<T> tree) throws IllegalArgumentException {
        if (!(tree instanceof BinaryTree)) {
            throw new IllegalArgumentException("The tree must be a binary tree");
        }

        Collection<T> nodes = initCollection(tree);

        if (tree.root() != null) {
            inOrder(nodes, (BinaryTree) tree, tree.root());
        }

        return nodes;
    }

    private void inOrder(Collection<T> nodes, BinaryTree<T> tree, Tree.Node<T> parent) {
        if (parent == null) {
            return;
        }

        Tree.Node<T> left = tree.leftOf(parent);
        if (left != null) {
            inOrder(nodes, tree, left);
        }

        nodes.add(parent.value());

        Tree.Node<T> right = tree.rightOf(parent);
        if (right != null) {
            inOrder(nodes, tree, right);
        }
    }

    protected Collection<T> initCollection(Tree<T> tree) {
        return new ArrayList<>(tree.size());
    }
}
