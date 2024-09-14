package thanos.storage;

import java.util.ArrayList;

import thanos.tasks.Task;

/**
 * The {@code IStorage} interface defines the methods for loading and saving tasks.
 */
public interface IStorage {
    /**
     * Loads the list of tasks from the storage.
     *
     * @return an {@code ArrayList} containing the tasks loaded from storage.
     */
    ArrayList<Task> load();

    /**
     * Saves the provided list of tasks to the storage.
     *
     * @param taskList an {@code ArrayList} containing the tasks to be saved.
     */
    void save(ArrayList<Task> taskList);
}
