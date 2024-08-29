package wenjigglybot;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    static List<Task> tasks = new ArrayList<>(100);

    /**
     * Retrieves the list of tasks.
     *
     * @return A {@link List} of {@link Task} objects.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The {@link Task} to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param i The index of the task to retrieve.
     * @return The {@link Task} at the specified index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param i The index of the task to remove.
     */
    public void remove(int i) {
        tasks.remove(i);
    }
}