package nugget;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Nugget task tracker.
 * Provides methods for task manipulation including adding, deleting, marking, unmarking,
 * and finding tasks that match a search query.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTask(int index) {
        tasks.get(index).markTask();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmarkTask();
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that match a given search query.
     * The search is case-insensitive and looks for tasks containing the query string.
     *
     * @param match The string to match against task descriptions.
     * @return An ArrayList of tasks that match the search query.
     */
    public ArrayList<Task> findMatchingTasks(String match) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(match.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
