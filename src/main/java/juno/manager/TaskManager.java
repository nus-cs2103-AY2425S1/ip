package juno.manager;

import java.util.ArrayList;

import juno.task.Task;

/**
 * Class to manage tasks and providing access to the task list.
 * Stores the list of tasks as an ArrayList.
 */
public class TaskManager {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskManager instance that takes in a specified list of tasks.
     * If the provided list is null, such as when the user just started the chat bot for the first time,
     * a new empty ArrayList is initialised.
     *
     * @param tasks the list of tasks that the user has.
     */
    public TaskManager(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return an ArrayList containing the tasks
     */
    public ArrayList<Task> getTasksArray() {
        return this.tasks;
    }

    /**
     * Checks if a task with the specified description already exists in the task list.
     *
     * @param taskDescription the description of the task to check for duplicates with the current task list.
     * @return true if a task with the same description exists, else, return false.
     */
    public boolean isDuplicateTask(String taskDescription) {
        assert this.tasks != null : "Task list cannot be null!";
        boolean isDuplicate = this.tasks.stream()
                .anyMatch(task -> task.getDescription().equalsIgnoreCase(taskDescription));
        return isDuplicate;
    }
}
