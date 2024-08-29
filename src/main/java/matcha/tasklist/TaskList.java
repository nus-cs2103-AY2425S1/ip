package matcha.tasklist;

import java.util.ArrayList;

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
     * Adds a task to the list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
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
        if (tasks.size() <= 0) {
            return "You have no tasks in the list.";
        }
        String str = ("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            String task = (i + 1) + ". " + tasks.get(i);
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
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
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
