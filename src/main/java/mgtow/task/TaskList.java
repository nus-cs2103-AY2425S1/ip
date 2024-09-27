package mgtow.task;

import mgtow.util.InvalidTaskException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Manages a list of tasks in the Mgtow application.
 * This class provides methods for adding, deleting, and manipulating tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
        assert tasks.contains(task) : "Task was not added successfully";
        System.out.println("\tOk can. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a new task from the list.
     *
     * @param index The index of the task to be deleted.
     * @return task that is removed.
     */
    public Task deleteTask(int index) throws InvalidTaskException {
        assert index >= 0 && index < tasks.size() : "Invalid task index";
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task removedTask = tasks.remove(index);
        assert !tasks.contains(removedTask) : "Task was not removed successfully";
        System.out.println("\tSigh. I've removed this task:");
        System.out.println("\t  " + removedTask);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        return removedTask;
    }

    /**
     * Marks the task as done.
     *
     * @param index The index of the task to be marked.
     * @throws InvalidTaskException If there is no task in list or index is invalid.
     */
    public void markTask(int index) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = tasks.get(index);
        task.markDone();
        assert task.getStatus().equals("X") : "Task was not marked as done";
        System.out.println("\t0.o I've marked this as done: ");
        System.out.println("\t  " + task);
    }

    /**
     * Marks the task as not done.
     *
     * @param index The index of the task to be marked.
     * @throws InvalidTaskException If there is no task in list or index is invalid.
     */
    public void unmarkTask(int index) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = tasks.get(index);
        task.markNotDone();
        System.out.println("\tAiyo, the task is marked as not done: ");
        System.out.println("\t  " + task);
    }

    public void listAllTasks() {
        System.out.println("\tSo many things to do...");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i));
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves all tasks scheduled for a specific date.
     *
     * @param date The date to check for tasks.
     * @return An ArrayList of tasks scheduled for the given date.
     */
    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        assert date != null : "Date cannot be null";
        return tasks.stream()
                .filter(task -> task.isOnDate(date))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Searches for tasks containing the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks whose descriptions contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Search keyword cannot be null or empty";
        return tasks.stream()
                .filter(task -> task.getDesc().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void sortTasks() {
        Collections.sort(tasks);
    }

    public ArrayList<Task> getSortedTasks() {
        ArrayList<Task> sortedTasks = new ArrayList<>(tasks);
        Collections.sort(sortedTasks);
        return sortedTasks;
    }
}