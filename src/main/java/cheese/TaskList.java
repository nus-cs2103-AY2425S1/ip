package cheese;

import java.util.ArrayList;

import cheese.task.Task;

/**
 * Stores the list of tasks for the chat bot
 * All operations are the same as ArrayList
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public Task remove(int idx) {
        return tasks.remove(idx);
    }
}
