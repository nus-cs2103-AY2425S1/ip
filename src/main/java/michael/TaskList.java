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

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void sort() {
        Collections.sort(tasks);
    }
}
