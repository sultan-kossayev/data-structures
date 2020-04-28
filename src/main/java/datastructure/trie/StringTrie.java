package datastructure.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the trie that stores strings
 */
public class StringTrie implements Trie<String> {

    /**
     * The root of the trie
     */
    private Node root;

    /**
     * The size of the trie
     */
    private int size;

    public StringTrie() {
        size = 0;
    }

    @Override
    public boolean insert(String val) {
        int beforeSize = size();
        root = insert(val, 0, root);

        return beforeSize != size();
    }

    private Node insert(String val, int idx, Node node) {
        if (node == null) {
            node = new Node();
        }

        if (val.length() == idx) {
            if (!node.isEnd()) { // new word
                size++;
            }
            node.markEnd();
            return node;
        }

        char ch = val.charAt(idx);
        Node child = insert(val, idx + 1, node.get(ch));
        node.add(val.charAt(idx), child);

        return node;
    }

    @Override
    public boolean exists(String val) {
        Node last = find(val, 0, root);

        return last != null ? last.end : false;
    }

    private Node find(String val, int idx, Node node) {
        if (node == null || val.length() == idx) {
            return node;
        }

        return find(val, idx + 1, node.get(val.charAt(idx)));
    }

    @Override
    public boolean remove(String val) {
        int beforeSize = size();

        remove(val, 0, root);

        return beforeSize != size();
    }

    private boolean remove(String val, int idx, Node node) {
        if (val.length() == idx) {
            if (node != null && node.isEnd()) {
                size--;
                node.unMarkEnd();

                return !node.isPrefix(); // if it is not prefix then it can be removed
            } else {
                return false;
            }
        }

        if (node != null) {
            char ch = val.charAt(idx);
            Node child = node.get(val.charAt(idx));
            if (remove(val, idx + 1, child)) {
                node.remove(ch);
            }

            return !node.isPrefix();
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node {
        Map<Character, Node> nodes;
        boolean end;

        public Node() {
            nodes = new HashMap<>();
        }

        void add(Character c, Node n) {
            nodes.put(c, n);
        }

        Node get(Character c) {
            return nodes.get(c);
        }

        void remove(Character c) {
            nodes.remove(c);
        }

        void markEnd() {
            end = true;
        }

        void unMarkEnd() {
            end = false;
        }

        boolean isEnd() {
            return end;
        }

        boolean isPrefix() {
            return !nodes.isEmpty();
        }
    }
}
