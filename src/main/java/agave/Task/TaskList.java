package agave.Task;

import agave.Util.AgaveException;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks, allowing tasks to be added, removed, and manipulated.
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
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
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
     * @param taskNumber The index of the task to be deleted.
     * @throws AgaveException If the task number is out of range.
     */
    public void deleteTask(int taskNumber) throws AgaveException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removed = tasks.remove(taskNumber - 1);
            System.out.println("Removed: " + removed);
            showNumberOfTasks();
        } else {
            throw new AgaveException("Task number is out of range. Enter a valid task number.");
        }
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        return tasks.get(index);
    }

    /**
     * Returns the last task in the list.
     *
     * @return The last task in the list.
     */
    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The index of the task to be marked as done.
     * @throws AgaveException If the task number is out of range.
     */
    public void markTask(int taskNumber) throws AgaveException {
        assert taskNumber > 0 : "Task number cannot be negative";
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).markAsDone();
        } else {
            throw new AgaveException("Task number is out of range. Please enter a valid task number.");
        }
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskNumber The index of the task to be unmarked.
     * @throws AgaveException If the task number is out of range.
     */
    public void unmarkTask(int taskNumber) throws AgaveException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).unmarkAsDone();
        } else {
            throw new AgaveException("Task number is out of range. Please enter a valid task number.");
        }
    }

    /**
     * Shows the number of tasks in the list.
     */
    public void showNumberOfTasks() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> findTasks(String key) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(key)) {
                result.add(task);
            }
        }
        return result;
    }
}