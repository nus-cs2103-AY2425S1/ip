package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents the list of tasks handled by the application.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor for a new list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a list of tasks.
     *
     * @param tasks The tasks to populate the list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        @SuppressWarnings("unchecked")
        ArrayList<Task> tasksClone = (ArrayList<Task>) tasks.clone();
        this.tasks = tasksClone;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Adds multiple tasks to the task list.
     *
     * @param tasks The list of tasks to add.
     */
    public void add(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Returns a task by item number.
     *
     * @param itemNum The item number of the task to return.
     * @return The task with the specified item number.
     * @throws BobException If task with the specified item number does not exist.
     */
    public Task get(int itemNum) throws BobException {
        if (itemNum <= 0 || itemNum > this.tasks.size()) {
            throw new BobException("I'm sorry, I could not find task number " + itemNum + ".");
        }

        return this.tasks.get(itemNum - 1);
    }

    /**
     * Deletes a task by item number, and returns it.
     *
     * @param itemNum The item number of the task to delete.
     * @return The deleted task with the specified item number.
     * @throws BobException If task with the specified item number does not exist.
     */
    public Task delete(int itemNum) throws BobException {
        Task task = this.get(itemNum);
        this.tasks.remove(itemNum - 1);
        return task;
    }

    /**
     * Marks a task as completed or not completed by item number, and returns it.
     *
     * @param itemNum The item number of the task to mark.
     * @param isCompleted Whether to mark the task as completed or not completed.
     * @throws BobException If the task with the specified item number does not exist.
     */
    public Task mark(int itemNum, boolean isCompleted) throws BobException {
        Task task = this.get(itemNum);
        task.mark(isCompleted);
        return task;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> all() {
        return this.tasks;
    }

    /**
     * Clears the list of tasks.
     */
    public void clear() {
        this.tasks = new ArrayList<>();
    }
}
