package cheese;

import cheese.task.Task;

import java.util.ArrayList;

/**
 * contains the task list
 * e.g., it has operations to add/delete tasks in the list
 * All operations are the same as ArrayList
 */
public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }
    TaskList(ArrayList<Task> tasks) {
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
