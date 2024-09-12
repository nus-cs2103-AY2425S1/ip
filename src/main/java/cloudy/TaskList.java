package cloudy;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks. It provides methods to add, remove,
 * retrieve, and manage tasks in the list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks The initial list of tasks to be managed by this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        tasks.add(task);
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public ArrayList<Task> findTasks(String searchQuery) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            return matchingTasks;
        }

        for (Task task : tasks) {
            if (task.description != null && task.description.contains(searchQuery)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }


}
