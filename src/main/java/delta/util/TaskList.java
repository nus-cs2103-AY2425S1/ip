package delta.util;

import java.util.ArrayList;

import delta.exception.DeltaException;
import delta.task.Task;

/**
 * Contains all the tasks in a list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor to create instance using saved tasks.
     * Only used by constructor of Delta.java.
     *
     * @param tasks Saved list of tasks to be stored in TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a specific task from the list indicated by index.
     *
     * @param index Index of task to be returned (zero-based indexing).
     */
    public Task getTask(int index) throws DeltaException {
        // List is empty
        if (tasks.isEmpty()) {
            throw new DeltaException("OOPS!!! List is empty! Please add tasks!");
        }

        // Index out of range of list
        if (index < 0 || index >= tasks.size()) {
            throw new DeltaException("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task.""");
        }

        return tasks.get(index);
    }

    /**
     * Returns size of list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds task into stored list.
     *
     * @param task Task to be added into list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks specific task in list as done.
     *
     * @param index Index of task to be marked (one-based indexing).
     * @return Task after it has been marked as done.
     * @throws DeltaException If list is empty, task not found in list or task is already marked as done.
     */
    public Task markTask(int index) throws DeltaException {
        Task task = getTask(index - 1);

        // Task already marked
        if (task.getStatusIcon().equals("X")) {
            throw new DeltaException("OOPS!!! Task is already marked as done.");
        }

        task.markAsDone();
        tasks.set(index - 1, task);
        return task;
    }

    /**
     * Marks specific task in list as not done yet.
     *
     * @param index Index of task to be unmarked (one-based indexing).
     * @return Task after it has been unmarked.
     * @throws DeltaException If list is empty, task not found in list or task is already unmarked.
     */
    public Task unmarkTask(int index) throws DeltaException {
        Task task = getTask(index - 1);

        // Task already unmarked
        if (task.getStatusIcon().equals(" ")) {
            throw new DeltaException("OOPS!!! Task is already marked as not done yet.");
        }

        task.markAsNotDone();
        tasks.set(index - 1, task);
        return task;
    }

    /**
     * Removes specific task from list.
     *
     * @param index Index of task to be removed from list (one-based indexing).
     * @return Task that was removed from list.
     * @throws DeltaException If list is empty or task not found in list.
     */
    public Task deleteTask(int index) throws DeltaException {
        Task task = getTask(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    /**
     * Edits specific task in list.
     *
     * @param index Index of task to be edited in list (one-based indexing).
     * @param newTask New task to replace old task in list.
     * @return New task that was added into list.
     * @throws DeltaException If list is empty or task not found in list.
     */
    public Task editTask(int index, Task newTask) throws DeltaException {
        Task task = getTask(index - 1);
        if (task.getStatusIcon().equals("X")) {
            newTask.markAsDone();
        }
        tasks.set(index - 1, newTask);
        return newTask;
    }
}
