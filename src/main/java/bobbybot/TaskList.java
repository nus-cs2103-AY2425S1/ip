package bobbybot;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList, initializes an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList, initializes the task list with the given tasks.
     *
     * @param tasks List of tasks to be added to the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked as done.
     * @return Task that has been marked as done.
     */
    public Task markDone(int index) {
        Task task = tasks.get(index);
        task.setDone(true);
        return task;
    }

    /**
     * Marks a task as undone.
     *
     * @param index Index of the task to be marked as undone.
     * @return Task that has been marked as undone.
     */
    public Task markUndone(int index) {
        Task task = tasks.get(index);
        task.setDone(false);
        return task;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets a task from the task list.
     *
     * @param index Index of the task to be retrieved.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of the task to be deleted.
     * @return Task that has been deleted.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task list as an array.
     *
     * @return Task list as an array.
     */
    public Task[] toArray() {
        return tasks.toArray(new Task[0]);
    }

    /**
     * Finds tasks that match the search query.
     *
     * @param query The search query.
     * @return A TaskList of tasks that match the search query.
     */
    public TaskList findTasks(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(query)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}
