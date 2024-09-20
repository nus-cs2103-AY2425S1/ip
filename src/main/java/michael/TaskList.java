package michael;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Keeps track of tasks to be managed by the chatbot
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the given position.
     *
     * @param index Position of task to be retrieved.
     * @return Task needed to be retrieved.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns how many tasks are currently in the list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes the task at the given position from the list.
     *
     * @param index Position of task to be deleted.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Sorts tasks according to their ordering.
     */
    public void sort() {
        Collections.sort(tasks);
    }
}
