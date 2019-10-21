package datastructure.tree.traversal;

import datastructure.tree.Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Traverses the tree using breadth-first traversal
 * @param <T>
 */
public class BreadthFirstTraversal<T> implements TreeTraversal<T> {

    @Override
    public Collection<T> traverse(Tree<T> tree) {
        Collection<T> values = initCollection(tree);

        if (tree.root() != null) {
            breadthFirst(values, tree, tree.root());
        }

        return values;
    }

    private void breadthFirst(Collection<T> values, Tree<T> tree, Tree.Node<T> root) {
        Queue<Tree.Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            root = queue.poll();
            values.add(root.value());

            queue.addAll(tree.childrenOf(root));
        }
    }

    protected Collection<T> initCollection(Tree<T> tree) {
        return new ArrayList<>(tree.size());
    }
}