package datastructure.trie;

import datastructure.DataStructure;

/**
 * The interface of the Trie data structure a.k.a. prefix tree
 */
public interface Trie<T> extends DataStructure {

    /**
     * Inserts a value to trie
     *
     * @param val the value to insert
     * @return true if trie didn't contain the value prior to insertion
     */
    boolean insert(T val);

    /**
     * Checks whether a value exists in the trie
     * @param val the value to chech
     * @return true if the trie contains the value
     */
    boolean exists(T val);

    /**
     * Removes a value from the trie
     * @param val the value to remove from the trie
     * @return true if the trie contained the value that was removed
     */
    boolean remove(T val);
}
