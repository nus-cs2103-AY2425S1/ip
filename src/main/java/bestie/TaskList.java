package bestie;

import bestie.task.Task;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Contains the list of tasks, and has operations to add or delete tasks in the list.
 */
public class TaskList {

    ArrayList<Task> tasks;

    /**
     * Initialises the task list, which is an ArrayList of Task objects.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param task Task to be added to the task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index Index of the task to be deleted from the list.
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    /**
     * Returns a task in the task list.
     * @param index Index of the task to be returned.
     * @return Task in the task list at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks in an ArrayList format.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                matchingTasks.add(tasks.get(i));
            }
        }
        return matchingTasks;
    }
}
