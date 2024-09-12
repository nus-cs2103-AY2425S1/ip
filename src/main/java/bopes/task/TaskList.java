package bopes.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import bopes.exception.BopesException;

/**
 * The TaskList class manages a list of tasks, allowing tasks to be added, removed,
 * marked as done or undone, and printed. It also provides functionality to check
 * if the list is empty and to retrieve the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the specified list of tasks.
     *
     * @param tasks an ArrayList of Task objects to initialize the TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the Task to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index the index of the task to be deleted (0-based)
     * @throws BopesException if the index is out of range (index < 0 or index >= tasks.size())
     */
    public void deleteTask(int index) throws BopesException {
        if (index < 0 || index >= tasks.size()) {
            throw new BopesException("Error: The task index is out of range.");
        }
        tasks.remove(index);
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index the index of the task to be marked as done (0-based)
     * @return the Task that was marked as done
     * @throws BopesException if the index is out of range (index < 0 or index >= tasks.size())
     */
    public Task markTask(int index) throws BopesException {
        if (index < 0 || index >= tasks.size()) {
            throw new BopesException("Error: The task index is out of range.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done at the specified index.
     *
     * @param index the index of the task to be marked as not done (0-based)
     * @return the Task that was marked as not done
     * @throws BopesException if the index is out of range (index < 0 or index >= tasks.size())
     */
    public Task unmarkTask(int index) throws BopesException {
        if (index < 0 || index >= tasks.size()) {
            throw new BopesException("Error: The task index is out of range.");
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * Returns a string representation of all tasks in the task list.
     * Each task is included with its index (1-based) and description.
     *
     * @return a string representing the list of tasks
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return result.toString().trim(); // Remove the trailing newline
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Prints all tasks in the task list to the console.
     * Each task is printed with its index (1-based) and description.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return an ArrayList of Task objects
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

/**
     * Searches for tasks that contain the keyword in their description.
     *
     * @param keyword the keyword to search for
     * @return a TaskList containing tasks that match the keyword
     */
    public String findTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
            .filter(task -> task.description.contains(keyword))
            .collect(Collectors.toList());
    
        if (matchingTasks.isEmpty()) {
            return "No such tasks found.";
        }
    
        return matchingTasks.stream()
            .map(Task::toString)
            .collect(Collectors.joining("\n"));
    }
}
