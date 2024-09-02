package tissue;

import java.util.ArrayList;

import tissue.task.Task;

/**
 * Wrapper class to store all the tasks added and related functions.
 */
public class TaskList {
    private final String INDENT = "       ";
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns task at the index.
     *
     * @param index The index of the task.
     * @return Task at the index.
     */
    public Task retrieveTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds specified task to the list.
     *
     * @param task The task to add to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        String parsedText = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            parsedText += INDENT + (i + 1) + "." + " " + task + "\n";
        }
        return parsedText;
    }

    /**
     * Deletes specified task from the list.
     *
     * @param number The task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int number) {
        return tasks.remove(number - 1);
    }

    /**
     * Searches for tasks that matches the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> searchKeyword(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTask().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }
}
