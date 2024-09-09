package matcha.tasklist;

import java.util.ArrayList;

import matcha.exception.MatchaException;
import matcha.task.Task;

/**
 * Represents a list of Tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the list of tasks. Checks if the task already exists in the list
     * before adding it.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) throws MatchaException {
        if (checkDuplicates(task)) {
            throw new MatchaException("This task already exists in the list! Task not added.");
        } else {
            this.tasks.add(task);
        }
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param taskNum Index of the task to be deleted.
     */
    public void deleteTask(int taskNum) {
        this.tasks.remove(taskNum);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param taskNum Index of the task.
     * @return Task at the specified index.
     */
    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a string representation of the tasks in the list.
     *
     * @return String representation of the tasks in the list.
     */
    public String listTasks() {
        assert tasks != null : "Task list should not be null";
        if (tasks.size() <= 0) {
            return "You have no tasks in the list.";
        }
        String str = ("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            assert t != null : "Task should not be null";
            String task = (i + 1) + ". " + t;
            str += ("\n\t" + task);
        }
        return str;
    }

    /**
     * Finds tasks that contain the given keyword and adds it to an ArrayList.
     *
     * @param keyword The keyword to search for.
     * @return An ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            assert task != null : "Task should not be null";
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Check if there is any duplicate task of the given task in the list.
     *
     * @param task Task to check for duplicates.
     * @return True if there is a duplicate, false otherwise.
     */
    public boolean checkDuplicates(Task task) {
        for (Task t : tasks) {
            if (t.checkDuplicate(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Shows the task that was updated in the list. Indicates
     * the number of tasks in the list after updating.
     *
     * @param task Task that was updated.
     * @return String representation of the updated task.
     */
    public String showTask(Task task) {
        return task.toString() + "\n" + "You have " + tasks.size() + " tasks in the list.";
    }
}
