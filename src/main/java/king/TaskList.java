package king;

import java.util.ArrayList;

import king.task.Task;

/**
 * The TaskList class encapsulates a list of tasks.
 * It provides operations to add, remove, retrieve, and manipulate tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the specified tasks.
     *
     * @param tasks the list of tasks to be managed by this TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on its index.
     *
     * @param index the index of the task to be removed
     */
    public void remove(int index) {
        // Assert that the index is within valid range
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    /**
     * Retrieves a task from the task list based on its index.
     *
     * @param index the index of the task to be retrieved
     * @return the task at the specified index, or null if the index is out of bounds
     */
    public Task getTask(int index) {
        // Assert that the index is within valid range
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;

        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    /**
     * Returns the list of tasks managed by this TaskList.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks a task as done based on its task number.
     *
     * @param taskNumber the task number to be marked as done
     * @throws KingException if the task number is invalid
     */
    public void markTaskAsDone(int taskNumber) throws KingException {
        // Assert that the taskNumber is within valid range
        assert taskNumber >= 0 && taskNumber < tasks.size() : "Index out of bounds: " + taskNumber;

        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsDone();
        } else {
            throw new KingException("Enter a valid task number within the list you buffoon!");
        }
    }

    /**
     * Marks a task as undone based on its task number.
     *
     * @param taskNumber the task number to be marked as undone
     * @throws KingException if the task number is invalid
     */
    public void markTaskAsUndone(int taskNumber) throws KingException {
        // Assert that the taskNumber is within valid range
        assert taskNumber >= 0 && taskNumber < tasks.size() : "Index out of bounds: " + taskNumber;

        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsUndone();
        } else {
            throw new KingException("Enter a valid task number within the list you buffoon!");
        }
    }
}
