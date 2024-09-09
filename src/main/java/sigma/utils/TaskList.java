package sigma.utils;

import java.util.ArrayList;

import sigma.task.Task;

/**
 * Represents a list of tasks.
 * Contains operations to add, remove, and get tasks.
 * Also contains operations to check if the list is empty and to get the size of the list.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if the list of tasks is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param i The index of the task to get.
     *
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param i The index of the task to remove.
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in the tasks.
     *
     * @return An array list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        tasks.stream()
                .filter((task) -> (task.getDescription().contains(keyword)))
                .forEachOrdered((task) -> {
                    matchingTasks.add(task);
                });
        return matchingTasks;
    }

    /**
     * Builds a list of tasks.
     *
     * @return A string builder containing the list of tasks.
     */
    public StringBuilder buildList() {
        StringBuilder s = new StringBuilder();
        tasks.stream()
                .map((task) -> tasks.indexOf(task) + 1)
                .map((i) -> i + ". " + tasks.get(i - 1).toString() + "\n")
                .forEachOrdered((taskString) -> {
                    s.append(taskString);
                });
        return s;
    }

}
