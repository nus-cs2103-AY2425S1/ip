package pebble;

import java.util.ArrayList;

/**
 * List that stores all the tasks.
 */
public class TasksList {
    /** List of tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TasksList object.
     */
    public TasksList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TasksList object with a list of tasks.
     *
     * @param tasks List of tasks.
     */
    public TasksList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets a task from the list.
     *
     * @param index Index of the task to be retrieved.
     * @return Task object.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public Task getTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return tasks.get(index);
    }


    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns a list of tasks that contains the keyword in its description.
     *
     * @param s Keyword to be searched.
     * @return List of tasks that contains the keyword.
     */
    public TasksList getFilteredList(String s) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            temp.add(tasks.get(i));
        }
        temp.removeIf(task -> !task.description.contains(s));
        return new TasksList(temp);
    }
}
