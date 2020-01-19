package datastructure.map;

import java.util.Objects;

/**
 * The implementation of the hash map.
 * The map uses a table with power of two capacity and separate chaining for collision resolution.
 *
 * Null keys are supported.
 */
public class HashMap<K, V> implements Map<K, V> {

    /**
     * The default capacity of the bucket array maintained by the map
     * Ensure that the value is power of two number.
     */
    private static int DEFAULT_CAPACITY = 1 << 4;

    /**
     * The default load factor i.e. total number of elements inserted into the map divided by
     * number of buckets available in the map.
     */
    private static float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The number of key-value pairs the map maintains
     */
    int size;

    /**
     * Number of elements after which the map has to be resized
     * (table.length * loadFactor)
     */
    int threshold;

    /**
     * The bucket array that contains the key value pairs
     */
    Node<K, V>[] buckets;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity, float loadFactor) {
        capacity = calculateBucketSizeFor(capacity);

        this.buckets = new Node[capacity];
        this.threshold = (int)(capacity * loadFactor);
    }

    static final int calculateBucketSizeFor(int capacity) {
        capacity = capacity - 1;
        return capacity <= 0? 1 : Integer.highestOneBit(capacity) << 1;
    }

    @Override
    public V get(K key) {
        int idx = indexForHash(hash(key));

        V value = null;
        Node<K, V> node = buckets[idx];
        while(node != null) {
            if (Objects.equals(node.key, key)) {
                value = node.value;
                break;
            }

            node = node.next;
        }

        return value;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int idx = indexForHash(hash);

        V oldVal = null;
        if (buckets[idx] == null) {
            buckets[idx] = new Node<>(key, value, hash);
            size++;
            resizeIfNeeded();

            return null;
        } else {
            Node<K, V> curr = buckets[idx];
            while(curr != null) {
                if (curr.key.equals(key)) {
                    oldVal = curr.value;
                    curr.value = value;
                    break;
                }

                if (curr.next == null) {
                    Node<K, V> newNode = new Node<>(key, value, hash);
                    curr.next = newNode;

                    size++;
                    resizeIfNeeded();

                    break;
                }

                curr = curr.next;
            }
        }

        return oldVal;
    }

    private void resizeIfNeeded() {
        if (size > threshold) {
            Node<K, V>[] oldBuckets = buckets;
            buckets = new Node[buckets.length * 2];
            threshold *= 2;

            for (int b = 0; b < oldBuckets.length; b++) {
                Node<K, V> node = oldBuckets[b];
                if (node != null) {
                    oldBuckets[b] = null;

                    if (node.next == null) {
                        buckets[indexForHash(node.hash)] = node;
                    } else {
                        Node<K, V> stayHead = null;
                        Node<K, V> stayTail = null;
                        Node<K, V> transferHead = null;
                        Node<K, V> transferTail = null;
                        while(node != null) {
                            boolean stays = (node.hash & oldBuckets.length) == 0;
                            if (stays) {
                                if (stayTail == null) {
                                    stayHead = node;
                                } else {
                                    stayTail.next = node;
                                }

                                stayTail = node;

                            } else {
                                if (transferTail == null) {
                                    transferHead = node;
                                } else {
                                    transferTail.next = node;
                                }

                                transferTail = node;
                            }

                            node = node.next;
                        }

                        if (stayHead != null) {
                            stayTail.next = null;
                            buckets[b] = stayHead;
                        }

                        if (transferHead != null) {
                            transferTail.next = null;
                            buckets[b + oldBuckets.length] = transferHead;
                        }
                    }
                }
            }
        }
    }

    private int indexForHash(int hash) {
        return hash & (buckets.length - 1);
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int hash = key.hashCode();
        hash = hash ^ (hash >>> 16);

        return hash;
    }

    @Override
    public V remove(K key) {
        int idx = indexForHash(hash(key));

        V val = null;

        Node<K, V> curr = buckets[idx];
        Node<K, V> prev = null;
        while(curr != null) {
            if (Objects.equals(curr.key, key)) {
                val = curr.value;
                if (prev == null) {
                    buckets[idx] = curr.next;
                } else {
                    prev.next = curr.next;
                }

                size--;

                break;
            }

            prev = curr;
            curr = curr.next;
        }

        return val;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = hash(key);

        Node<K, V> node = buckets[indexForHash(hash)];

        boolean has = false;
        while (node != null) {
            if (Objects.equals(node.key, key)) {
                has = true;
                break;
            }
            node = node.next;
        }

        return has;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        int hash;

        Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }
}
