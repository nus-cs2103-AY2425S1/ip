package carter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represent the list of tasks of the Carter application.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with existing list of tasks.
     *
     * @param tasks The list of task to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task into the TaskList.
     * After adding, the task list is sorted based on task type and specific attributes.
     *
     * @param task Task to be added to the TaskList.
     */
    public void addTask(Task task) {
        assert task != null : "task should not be null";
        tasks.add(task);
        Collections.sort(tasks);
    }

    /**
     * Deletes a task from TaskList with specified index.
     *
     * @param index The index of the task in TaskList.
     * @return The deleted task from the TaskList.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;
        return tasks.remove(index);
    }

    /**
     * Marks the task as done with specified index.
     *
     * @param index The index of the task in TaskList.
     * @return The marked task from TaskList.
     */
    public Task markTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task as not done with specified index.
     *
     * @param index The index of the task in TaskList.
     * @return The unmarked task from TaskList.
     */
    public Task unMarkTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Returns a new empty TaskList after archive all tasks.
     *
     * @return An empty Task List.
     */
    public TaskList archiveTask() {
        return new TaskList();
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a list of task with specified keyword.
     *
     * @param keyword The keyword to search in the tasks.
     * @return The list of tasks containing the keyword.
     */
    public List<Task> find(String keyword) {
        assert keyword != null : "keyword should not be null";
        List<Task> task = new ArrayList<>();
        for (Task t : tasks) {
            boolean isFound = t.toString().contains(keyword);
            if (isFound) {
                task.add(t);
            }
        }
        return task;
    }
}
