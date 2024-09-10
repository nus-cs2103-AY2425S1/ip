package seanbot;

import java.util.ArrayList;
import java.util.List;

import seanbot.Tasks.Task;

/**
 * The TaskList class for keeping track of current tasks
 */
public class TaskList {

    private ArrayList<Task> tasks;

    // Default constructor initializing an empty task list.
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Constructor initializing TaskList with existing tasks.
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Adds a task to the task list.
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Deletes a task from the task list by its index.
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    // Retrieves a task from the task list by its index.
    public Task getTask(int index) {
        return tasks.get(index);
    }

    // Returns the size of the task list.
    public int size() {
        return tasks.size();
    }

    // Returns the list of tasks.
    public ArrayList<Task> getTasks() {
        return tasks;
    }

/**
     * Finds and returns a list of tasks that match the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> found = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                found.add(task);
            }
        }
        return found;
    }
}