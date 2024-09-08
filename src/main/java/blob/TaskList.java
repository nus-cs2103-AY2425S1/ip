package blob;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles manipulation of tasks by working with 'storage'.
 * Constructor requires a Storage parameter.
 */
public class TaskList {
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Returns task at that particular index.
     * @param index desired index.
     * @return Task.
     */
    public Task getTask(int index) {
        try {
            ArrayList<Task> tempDatabase = this.storage.getFileContents();
            return tempDatabase.get(index);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        }
    }

    /**
     * @return integer number of existing tasks.
     */
    public int getSize() {
        try {
            ArrayList<Task> tempDatabase = this.storage.getFileContents();
            return tempDatabase.size();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        }
    }

    /**
     * Marks the task at the index as completed.
     * @param index index of task.
     */
    public void markTask(int index) {
        try {
            ArrayList<Task> tempDatabase = this.storage.getFileContents();
            Task t = tempDatabase.get(index);
            t.complete();
            this.storage.updateFileContents(tempDatabase);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException("Error updating database!");
        }
    }

    /**
     * Marks the task at the index as uncompleted.
     * @param index index of task.
     */
    public void unmarkTask(int index) {
        try {
            ArrayList<Task> tempDatabase = this.storage.getFileContents();
            Task t = tempDatabase.get(index);
            t.undo();
            this.storage.updateFileContents(tempDatabase);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException("Error updating database!");
        }
    }

    /**
     * Adds new task to the database.
     * @param task new Task object.
     */
    public void addTask(Task task) {
        try {
            ArrayList<Task> tempDatabase = this.storage.getFileContents();
            tempDatabase.add(task);
            this.storage.updateFileContents(tempDatabase);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException("Error updating database!");
        }
    }

    /**
     * Removes existing task from the database.
     * @param index index of task.
     */
    public Task deleteTask(int index) {
        try {
            ArrayList<Task> tempDatabase = this.storage.getFileContents();
            Task t = tempDatabase.get(index);
            tempDatabase.remove(t);
            this.storage.updateFileContents(tempDatabase);
            return t;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException("Error updating database!");
        }
    }
}
