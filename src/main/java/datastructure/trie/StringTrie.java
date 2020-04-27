package datastructure.trie;

import javax.xml.soap.Node;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a trie to store strings
 */
public class StringTrie implements Trie<String> {

    /**
     * The root of the trie
     */
    private Node root;

    public StringTrie() {
        root = new Node();
    }

    @Override
    public void insert(String val) {
        insert(val, 0, root);
    }

    private void insert(String val, int idx, Node parent) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
    private static class Node {
        Map<Character, Node> nodes;
        boolean end;

        public Node() {
            nodes = new HashMap<>();
        }
    }
}
