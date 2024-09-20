package chatsy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import chatsy.exceptions.EmptyDescriptionException;
import chatsy.exceptions.InvalidTaskIndexException;
import chatsy.exceptions.InvalidTaskStringException;
import chatsy.tasks.DeadlineTask;
import chatsy.tasks.Task;

/**
 * Manages the tasks in the Chatsy application.
 */
public class TaskManager {

    private List<Task> tasks;

    /**
     * Constructs a TaskManager with an empty task list.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @throws EmptyDescriptionException if the task description is empty.
     */
    public void addTask(Task task) throws EmptyDescriptionException {
        if (task == null || task.getDescription().trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public void deleteTask(int index) throws InvalidTaskIndexException {
        validateIndex(index);
        Task removedTask = tasks.remove(index - 1);
        System.out.println("Task deleted: " + removedTask);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public void markTask(int index) throws InvalidTaskIndexException {
        validateIndex(index);
        Task task = tasks.get(index - 1);
        task.markAsDone();
        System.out.println("Task marked as done: " + task);
    }

    /**
     * Unmarks a task, setting it as not done.
     *
     * @param index The index of the task to be unmarked.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public void unmarkTaskAsNotDone(int index) throws InvalidTaskIndexException {
        validateIndex(index);
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        System.out.println("Task marked as not done: " + task);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Sets the list of tasks.
     *
     * @param tasks The list of tasks to set.
     * @throws InvalidTaskStringException if the task string is invalid.
     */
    public void setTasks(List<Task> tasks) throws InvalidTaskStringException {
        if (tasks == null) {
            throw new InvalidTaskStringException();
        }
        this.tasks = tasks;
        System.out.println("Task list updated.");
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return The string representation of the task list.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "No tasks to list.";
        }

        StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listOutput.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOutput.toString();
    }

    /**
     * Finds tasks that match the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A string representation of the matching tasks.
     * @throws InvalidTaskStringException if keyword is null.
     */
    public String findTasks(String keyword) throws InvalidTaskStringException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new InvalidTaskStringException();
        }

        StringBuilder matchingTasks = new StringBuilder();
        int matchCount = 0;

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchCount++;
                matchingTasks.append(matchCount).append(". ").append(task).append("\n");
            }
        }

        return matchingTasks.toString();
    }

    /**
     * Validates the index of a task to ensure it is within the valid range.
     *
     * @param index The index of the task to be validated.
     * @throws InvalidTaskIndexException If the index is out of bounds.
     */
    private void validateIndex(int index) throws InvalidTaskIndexException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Sorts tasks with deadlines chronologically.
     */
    public void sortDeadlinesChronologically() {
        tasks.sort(Comparator.comparing(task -> {
            if (task instanceof DeadlineTask) {
                return ((DeadlineTask) task).getBy(); // Sort by deadline
            }
            return null;
        }));
    }

    /**
     * Sorts tasks with deadlines in reverse chronological order.
     */
    public void sortDeadlinesReverseChronologically() {
        tasks.sort(Comparator.comparing(task -> {
            if (task instanceof DeadlineTask) {
                return ((DeadlineTask) task).getBy();
            }
            return null;
        }).reversed());
    }
}
