package bobby;

import bobby.exception.BobbyException;

import java.util.ArrayList;

/**
 * The TaskList class manages the Task operations.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified ArrayList of Task.
     *
     * @param taskList The list of tasks in Bobby.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    /**
     * Returns total number of tasks in the list.
     * @return Number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return this.listOfTasks.size();
    }

    /**
     * Returns a Task at the desired index.
     *
     * @param x The index of the desired task.
     * @return Task at index x of the list.
     */
    public Task getTask(int x) {
        return this.listOfTasks.get(x);
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t Task to be added to the list.
     */
    public void addTask(Task t) {
        this.listOfTasks.add(t);
    }

    /**
     * Indicates a task as completed.
     *
     * @param x The index of the task as shown in the list to be indicated as completed.
     * @throws BobbyException If the user does not enter a valid index
     */
    public void mark(int x) throws BobbyException {
        if (x > this.listOfTasks.size() || x <= 0) {
            throw new BobbyException("My apologies. There is no task at that number!");
        }
        Task t = this.listOfTasks.get(x - 1);
        t.indComplete();
    }

    /**
     * Indicate a task to be incomplete.
     *
     * @param x The index of the task as shown in the list to be indicated as incomplete.
     * @throws BobbyException If the user does not enter a valid index
     */
    public void unmark(int x) throws BobbyException {
        if (x > this.listOfTasks.size() || x <= 0) {
            throw new BobbyException("My apologies. There is no task at that number!");
        }
        Task t = this.listOfTasks.get(x - 1);
        t.indIncomplete();
    }

    /**
     * Removes a task from the list
     *
     * @param x The index of the task as shown in the list to be removed.
     */
    public void deleteTask(int x) {
        this.listOfTasks.remove(x - 1);
    }

    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : this.listOfTasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
