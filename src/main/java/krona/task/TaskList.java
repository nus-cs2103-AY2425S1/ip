package krona.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Supports add, delete methods.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes the task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task
     * @param task task to be added
     */
    public void addTask(Task task) {
        int initialSize = tasks.size();
        tasks.add(task);
        assert tasks.size() == initialSize + 1 : "Task list size should have increased by 1";
    }

    /**
     * Deletes a task at the index
     * @param index of task to be deleted
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        int initialSize = tasks.size();
        tasks.remove(index);
        assert tasks.size() == initialSize - 1 : "Task list size should have decreased by 1";
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
