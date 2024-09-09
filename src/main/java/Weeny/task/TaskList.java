package weeny.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a list of tasks, allowing addition, deletion, and status updates.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initializes a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to remove.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as done by its index.
     *
     * @param index The index of the task to mark.
     */
    public void markAsDone(int index) {
        tasks.get(index).setAsDone();
    }

    /**
     * Unmarks a task as not done by its index.
     *
     * @param index The index of the task to unmark.
     */
    public void markAsNotDone(int index) {
        tasks.get(index).setAsNotDone();
    }

    /**
     * Find tasks that contains keyword
     *
     * @param keyWord String that we are looking for in a task
     * @return ArrayList of tasks that contains keyWord
     */
    public List<Task> findTask(String keyWord) {
        List<Task> searchResult = getTasks().stream().filter(task -> task.containString(keyWord))
                .collect(Collectors.toList());
        return searchResult;
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
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
