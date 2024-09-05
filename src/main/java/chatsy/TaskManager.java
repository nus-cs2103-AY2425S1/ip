package chatsy;

import java.util.ArrayList;
import java.util.List;

import chatsy.exceptions.InvalidTaskIndexException;
import chatsy.tasks.Task;


/**
 * Manages the tasks in the Chatsy application.
 */
public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public void deleteTask(int index) throws InvalidTaskIndexException {
        validateIndex(index);
        tasks.remove(index - 1);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public void markTask(int index) throws InvalidTaskIndexException {
        validateIndex(index);
        tasks.get(index - 1).markAsDone();
    }

    /**
     * Unmarks a task, setting it as not done.
     *
     * @param index The index of the task to be unmarked.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public void unmarkTask(int index) throws InvalidTaskIndexException {
        validateIndex(index);
        tasks.get(index - 1).markAsNotDone();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the list of tasks.
     *
     * @param tasks The list of tasks to set.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return The string representation of the task list.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "No tasks to list.";
        } else {
            StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                listOutput.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return listOutput.toString();
        }
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
     * Finds tasks that match the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A string representation of the matching tasks.
     */
    public String findTasks(String keyword) {
        StringBuilder matchingTasks = new StringBuilder();
        int matchCount = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchCount++;
                matchingTasks.append(matchCount).append(". ").append(task).append("\n");
            }
        }

        return matchingTasks.toString();
    }
}
