package barney.data;

import java.util.ArrayList;

import barney.data.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks The tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task pop(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a string representation of the TaskList.
     *
     * @return A string representation of the TaskList.
     */
    public String toStringList() {
        if (tasks.size() == 0) {
            return "No tasks in the list!";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns the ArrayList of tasks in the TaskList.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getArrayList() {
        return tasks;
    }

    /**
     * Returns a TaskList containing tasks that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing tasks that match the given keyword.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

}
