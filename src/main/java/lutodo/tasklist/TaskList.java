package lutodo.tasklist;

import java.util.ArrayList;

import lutodo.tasks.Task;

/**
 * Contains the task list.
 */
public class TaskList {
    public static final Task EMPTY_TASK = new Task("default");

    private ArrayList<Task> tasks;

    /**
     * Constructs the TaskList object in default.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs the TaskList object with the ArrayList containing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task object to the taskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task.
     *
     * @param index The index of task to be deleted. 1 represents the first task.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets the task in the task list.
     * @param index The index of task to be gotten. 1 represents the first task.
     * @return The task to be gotten.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently.
     *
     * @return Number of tasks currently.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Shows whether the task list is empty.
     * @return A boolean showing whether the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}
