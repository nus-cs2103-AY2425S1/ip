package thanos.storage;

import java.util.ArrayList;

import thanos.tasks.Task;

/**
 * The {@code IStorage} interface defines the methods for loading and saving tasks.
 * <p>
 * This interface is intended to be implemented by classes responsible for managing the storage
 * of tasks, whether in a file, database, or other persistent storage system. It provides methods
 * to load tasks from storage and to save tasks to storage.
 * </p>
 */
public interface IStorage {
    /**
     * Loads the list of tasks from the storage.
     * <p>
     * This method retrieves the tasks stored in the persistent storage and returns them as an
     * {@code ArrayList} of {@code Task} objects.
     * </p>
     *
     * @return an {@code ArrayList} containing the tasks loaded from storage.
     */
    ArrayList<Task> load();

    /**
     * Saves the provided list of tasks to the storage.
     * <p>
     * This method stores the given {@code ArrayList} of {@code Task} objects in the persistent
     * storage, ensuring that the current state of tasks is preserved.
     * </p>
     *
     * @param taskList an {@code ArrayList} containing the tasks to be saved.
     */
    void save(ArrayList<Task> taskList);
}
