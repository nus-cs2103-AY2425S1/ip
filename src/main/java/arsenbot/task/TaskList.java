package arsenbot.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks managed by ArsenBot.
 * Provides operations to add, delete, and retrieve tasks from the list.
 */

public class TaskList {

    private final List<Task> tasks;

    /**
     * Construct an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs an empty TaskList with a predefined list of tasks.
     * @param tasks the list of tasks to initialize the TaskList with
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
