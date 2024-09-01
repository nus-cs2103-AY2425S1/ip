package optimus;

import optimus.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> record;

    public TaskList() {
        this.record = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.record = tasks;
    }

    public void addTask(Task task) {
        record.add(task);
    }

    public void deleteTask(int index) {
        record.remove(index);
    }

    public Task getTask(int index) {
        return record.get(index);
    }

    public int size() {
        return record.size();
    }

    public List<Task> getTasks() {
        return record;
    }
}
