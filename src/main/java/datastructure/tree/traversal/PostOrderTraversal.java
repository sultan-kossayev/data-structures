package datastructure.tree.traversal;

import datastructure.tree.Tree;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Traverses the binary tree using postorder traversal
 */
public class PostOrderTraversal<T> implements TreeTraversal<T> {

    @Override
    public Collection<T> traverse(Tree<T> tree) {
        Collection<T> nodes = initCollection(tree);

        if (tree.root() != null) {
            postOrder(nodes, tree, tree.root());
        }

        return nodes;
    }

    private void postOrder(Collection<T> nodes, Tree<T> tree, Tree.Node<T> parent) {
        if (parent == null) {
            return;
        }

        for (Tree.Node<T> child : tree.childrenOf(parent)) {
            postOrder(nodes, tree, child);
        }

        nodes.add(parent.value());
    }

    protected Collection<T> initCollection(Tree<T> tree) {
        return new ArrayList(tree.size());
    }
}
