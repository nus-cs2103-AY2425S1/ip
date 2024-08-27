package nathanbot.tasks;


import java.util.ArrayList;

import nathanbot.storage.Storage;

/**
 * Manages a list of tasks and handles operations such as adding, marking as done, and deleting tasks.
 * Javadocs using Copilot
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;

    /**
     * Constructs a TaskList with the specified storage.
     *
     * @param storage The storage to be used for loading and saving tasks.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.taskList = storage.loadTasksFromFile();
    }

    /**
     * Adds a task to the task list and saves the updated list to storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
        storage.saveTasksToFile(taskList);
    }

    /**
     * Marks the task at the specified index as done and saves the updated list to storage.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
        storage.saveTasksToFile(taskList);
    }

    /**
     * Marks the task at the specified index as undone and saves the updated list to storage.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void markAsUndone(int index) {
        taskList.get(index).unmarkAsDone();
        storage.saveTasksToFile(taskList);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes the task at the specified index and saves the updated list to storage.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
        storage.saveTasksToFile(taskList);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int listLength() {
        return taskList.size();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "You have no tasks in the list.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}