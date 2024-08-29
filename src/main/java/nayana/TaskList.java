package nayana;

import nayana.task.*;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * This class manages a collection of tasks and provides methods to add, remove, and list them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with an initial set of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task delete(int index) throws NayanaException {
        if (index < 0 || index >= tasks.size()) {
            throw new NayanaException("Invalid task index.");
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The updated task.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task markAsDone(int index) throws NayanaException {
        if (index < 0 || index >= tasks.size()) {
            throw new NayanaException("Invalid task index.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return The updated task.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task markAsNotDone(int index) throws NayanaException {
        if (index < 0 || index >= tasks.size()) {
            throw new NayanaException("Invalid task index.");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Returns a list of all tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a string representation of the task list.
     * Each task is represented with an index and a description.
     *
     * @return A string listing all tasks with their indices.
     */
    @Override
    public String toString() {
        StringBuilder listOfTasks = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOfTasks.toString();
    }

    public ArrayList<Task> findTasks(String findValue) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getDescription().contains(findValue)) {
                foundTasks.add(currTask);
            }
        }
        return foundTasks;
    }
}
