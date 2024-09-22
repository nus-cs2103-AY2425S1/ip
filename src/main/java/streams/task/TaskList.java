
package streams.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import streams.exception.StreamsException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @throws StreamsException If the index is invalid.
     */
    public void deleteTask(int index) throws StreamsException {
        if (index < 0 || index >= tasks.size()) {
            throw new StreamsException("invalid task number");
        }
        tasks.remove(index);
    }

    /**
     * Gets a task from the list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws StreamsException If the index is invalid.
     */
    public Task getTask(int index) throws StreamsException {
        if (index < 0 || index >= tasks.size()) {
            throw new StreamsException("invalid task number");
        }
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Gets all tasks in the list.
     *
     * @return A copy of the list of all tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }


    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
