package sigma;

import sigma.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Contains operations to add, remove, and get tasks.
 * Also contains operations to check if the list is empty and to get the size of the list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
