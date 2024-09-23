package trackie.storage;

import trackie.tasks.Task;
import trackie.ui.TrackieException;

import java.util.ArrayList;

/**
 * Represents a task list designed for Trackie.
 * This class manages a collection of <code>Tasks</code> and provides methods to add, list, mark, unmark, and delete tasks.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list contains no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the tasklist.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as completed.
     */
    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Marks a task as not completed.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markUndone();
    }

    /**
     * Deletes a task from the task list.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Searches for task(s) whose descriptions contain the specified query string.
     *
     * Note that the query string just has to be a substring of the task's description
     * for the task to fulfil the query.
     *
     * @param query The search string to match against task descriptions.
     * @return An ArrayList of Task objects whose descriptions contain the query string.
     * @throws TrackieException If an error occurs during the search process.
     */
    public ArrayList<Task> findTasks(String query) throws TrackieException {
        ArrayList<Task> tasksMatchingQuery = new ArrayList<>();
        int noOfTasksFound = 0;
        for (Task t : tasks) {
            if (t.getDescription().contains(query)) {
                tasksMatchingQuery.add(t);
                noOfTasksFound++;
            }
        }
        return tasksMatchingQuery;
    }
}
