package storage;

import java.util.List;

/**
 * Represents an abstract storage system for managing data.
 * Subclasses should implement the methods to handle the specific storage mechanisms.
 */
public abstract class Storage {

    /**
     * Loads all the data from the storage.
     *
     * @return A list of strings representing the data loaded from storage.
     */
    public abstract List<String> load();

    /**
     * Stores the given element in the storage.
     *
     * @param elem The element to be stored.
     * @return A list of strings representing the data after the new element has been stored.
     */
    public abstract List<String> store(String elem);

    /**
     * Overwrites the existing data in the storage with the provided new data.
     *
     * @param data The new data to overwrite the existing data in the storage.
     * @return A list of strings representing the data after the update.
     */
    public abstract List<String> update(List<String> data);
}
