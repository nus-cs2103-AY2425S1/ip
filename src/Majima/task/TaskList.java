package Majima.task;

import Majima.MajimaException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask (int index) throws MajimaException {
        if (index < 0 || index >= tasks.size()) {
            throw new MajimaException("Kiryu! That number ain't even there!");
        }
        return tasks.remove(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}

