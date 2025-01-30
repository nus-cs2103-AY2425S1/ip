package star.main;

import java.util.ArrayList;

import star.task.Task;

/**
 * Represents the list of tasks and has operations to manipulate the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs the TaskList using an existing task list.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the tasklist.
     * @param task which is the task to add.
     */
    public void addTask(Task task) {
        assert task != null;
        this.tasks.add(task);
    }

    /**
     * Removes a task with the specified index from the tasklist.
     * @param index which is the index of the task to be removed.
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < this.tasks.size();
        this.tasks.remove(index);
    }

    /**
     * Gets the task at the specified index from the tasklist.
     * @param index which is the index of the task to retrieve.
     * @return the task at the given index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < this.tasks.size();
        return this.tasks.get(index);
    }

    /**
     * Gets the number of tasks in the tasklist.
     * @return the length and hence number of tasks in the tasklist.
     */
    public int length() {
        return this.tasks.size();
    }

    /**
     * Checks whether the tasklist is empty or not.
     * @return True if tasklist is empty and False otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the list of tasks.
     * @return an ArrayList of all the tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks the task at the specified index in the tasklist done.
     * @param zeroIndex which is the index of the task to be marked done.
     */
    public void markDone(int zeroIndex) {
        assert zeroIndex >= 0 && zeroIndex < this.tasks.size();
        tasks.get(zeroIndex).markDone();
    }

    /**
     * Marks the task at the specified index in the tasklist undone.
     * @param zeroIndex which is the index of the task to be marked undone.
     */
    public void markUndone(int zeroIndex) {
        assert zeroIndex >= 0 && zeroIndex < this.tasks.size();
        tasks.get(zeroIndex).markUndone();
    }

    /**
     * Finds tasks in the tasklist that contain the given keyword.
     * @param keyword String to find tasks in the tasklist containing that keyword in their description.
     * @return an ArrayList of tasks containing the given keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> findTaskList = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                findTaskList.add(task);
            }
        }
        return findTaskList;
    }
}
