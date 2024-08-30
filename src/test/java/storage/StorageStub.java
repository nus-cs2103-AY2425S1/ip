package storage;

import task.Task;
import java.util.ArrayList;

public class StorageStub extends Storage {
    private ArrayList<Task> tasks = new ArrayList<>();
    public boolean isSaveTasksCalled = false;

    public StorageStub() {
        super("");  // No need to specify a file path since it's a stub.
    }

    @Override
    public ArrayList<Task> loadTasks() {
        return new ArrayList<>(tasks);  // Return a copy of the internal task list to simulate loading from a file.
    }

    @Override
    public void saveTasks(ArrayList<Task> taskList) {
        tasks = new ArrayList<>(taskList);  // Simulate saving tasks by storing them in the internal list.
        isSaveTasksCalled = true;  // Indicate that saveTasks was called.
    }

    // Additional method to manually set tasks (useful for setting up specific test scenarios)
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    // Additional method to check the saved tasks directly (useful for assertions in tests)
    public ArrayList<Task> getSavedTasks() {
        return new ArrayList<>(tasks);
    }
}
