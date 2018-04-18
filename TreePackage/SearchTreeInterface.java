package TreePackage;

import java.util.Iterator;

public interface SearchTreeInterface<T extends Comparable<? super T>> extends TreeInterface<T>
{
    /** Searches for a specific entry in this tree.
     * @param anEntry An object to be found
     * @return True if the object was found in the tree */
    boolean contains(T anEntry);

    /** Retrieves a specific entry in this tree
     * @param anEntry an object to be found
     * @return Either that object that was found in the tree or null if no such object exists */
    T getEntry(T anEntry);

    /** Adds a new entry to this tree, if it does not match an existing object in the tree.
     * Otherwise, replaces the existing object with the new entry
     * @param anEntry An object to be added to the tree
     * @return Either null if anyEntry was not in the tree but has been added, or the existing
     * entry that matched the parameter anEntry and has been replaced in the tree */
    T add(T anEntry);

    /** Removes a specific entry from this tree
     * @param anEntry An object to be removed
     * @return Either the object that was removed from the tree or null if no such object exists
     */
    T remove(T anEntry);

    /** Creates an iterator that traverses all entries in this tree.
     * @return An iterator that provides sequential and ordered access to the entries in this tree
     */
    public Iterator<T> getInorderIterator();

    public T getMin();

    public T getMax();
}
