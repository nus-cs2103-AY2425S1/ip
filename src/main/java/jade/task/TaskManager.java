package jade.task;

import java.util.ArrayList;

import jade.command.FindCommand;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * Manages a list of tasks.
 */
public class TaskManager {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskManager object with the specified storage.
     *
     * @param storage The path of the file where tasks are stored.
     */
    public TaskManager(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>(storage.loadTasks());
    }

    /**
     * Gets a task to the task list.
     *
     * @param index The index of the task to get.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    /**
     * Marks a task from the task list.
     *
     * @param index The index of the task to be marked.
     * @param isDone Whether the task has been marked.
     */
    public void markTask(int index, boolean isDone) {
        if (isValidTaskIndex(index)) {
            if (isDone) {
                tasks.get(index).markAsDone();
            } else {
                tasks.get(index).markAsNotDone();
            }
            storage.saveTasks(tasks);
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be removed.
     * @throws JadeException if index is invalid.
     */
    public void deleteTask(int index) throws JadeException {
        if (isValidTaskIndex(index)) {
            tasks.remove(index);
            storage.saveTasks(tasks);
        } else {
            throw new JadeException("Hmm, no such task. Try again.");
        }
    }

    /**
     * Checks if the given index is a valid task index.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }
}
