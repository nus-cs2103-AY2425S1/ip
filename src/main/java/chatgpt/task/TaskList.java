package chatgpt.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *  The TaskList class handles the operations on list of Tasks.
 */
public class TaskList {
    /** List of all the tasks **/
    private ArrayList<Task> tasks;

    /**
     * Default constructor for empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList that stores the list of tasks given.
     *
     * @param tasks is the ArrayList of Task to be stored
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the given task to the list.
     *
     * @param task to be added to the list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task at the given index within the list.
     *
     * @param index of the task to be removed
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the task at the given index within the list.
     *
     * @param index of the task to return
     * @return Task at the given index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the amount of tasks within the list.
     *
     * @return int of number of tasks within the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns true when the list has tasks or false when the list has no task within.
     *
     * @return boolean on whether the list is empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns a TaskList containing all the task that contain the keyword.
     *
     * @param keyword to search for within the task
     * @return TaskList of the found task containing the keyword
     */
    public TaskList find(String keyword) {
        ArrayList<Task> res = tasks.stream()
                .filter(task -> task
                        .toString()
                        .contains(keyword))
                .collect(Collectors
                        .toCollection(ArrayList::new));
        return new TaskList(res);
    }
}
