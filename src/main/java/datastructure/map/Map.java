package datastructure.map;

import datastructure.DataStructure;

/**
 * Interface of the map data structure
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public interface Map<K, V> extends DataStructure {

    /**
     * Returns the value associated with key
     * or null if such key doesn't exist in the map
     * @param key
     * @return value or null
     */
    V get(K key);

    /**
     * Puts the key and value to the map.
     * If such key didn't exist before return null,
     * otherwise returns the old value associated with the key
     *
     * @param key
     * @param value
     * @return old value or null
     */
    V put(K key, V value);

    /**
     * Removes the value associated with the key.
     *
     * @param key
     * @return value or null if such key doesn't exist in the map
     */
    V remove(K key);

    /**
     * Allows to check whether the map contains a given key.
     * @param key to check
     * @return true if the key exists in the map or otherwise false
     */
    boolean containsKey(K key);
}
