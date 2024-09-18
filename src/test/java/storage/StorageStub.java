package storage;

import java.util.ArrayList;

import task.Task;

/**
 * The StorageStub class is a stub version of the Storage class used for testing purposes.
 * It simulates loading and saving tasks to and from a file by using an internal list of tasks.
 * This class is particularly useful for unit testing, as it avoids the need for actual file operations.
 */
public class StorageStub extends Storage {

    private ArrayList<Task> tasks = new ArrayList<>();
    private boolean isSaveTasksCalled = false;

    /**
     * Constructs a StorageStub instance with an empty task list.
     * No file path is required since this is a stub class.
     */
    public StorageStub() {
        // No need to specify a file path since it's a stub.
        super(" ");
    }

    /**
     * Returns whether the saveTasks method has been called.
     *
     * @return true if saveTasks was called, false otherwise.
     */
    public boolean getIsSaveTasksCalled() {
        return this.isSaveTasksCalled;
    }

    /**
     * Simulates loading tasks from a file by returning a copy of the internal task list.
     *
     * @return A copy of the internal task list.
     */
    @Override
    public ArrayList<Task> loadTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Simulates saving tasks to a file by storing them in the internal task list.
     * Sets a flag to indicate that the saveTasks method was called.
     *
     * @param taskList The list of tasks to be saved.
     */
    @Override
    public void saveTasks(ArrayList<Task> taskList) {
        tasks = new ArrayList<>(taskList);
        isSaveTasksCalled = true;
    }

    /**
     * Returns the internal task list that was saved.
     * This method is useful for assertions in tests to verify saved tasks.
     *
     * @return The list of tasks saved in the internal task list.
     */
    public ArrayList<Task> getSavedTasks() {
        return tasks;
    }
}
