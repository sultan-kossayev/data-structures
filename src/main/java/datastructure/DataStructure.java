package datastructure;

/**
 * The root interface of any data structure
 */
public interface DataStructure {

    /**
     * Checks whether the data structure is empty
     * @return true if the data structure is empty
     */
    boolean isEmpty();

    /**
     * Returns the size of the data structure
     * @return the number of elements that the data structure contains
     */
    int size();
}