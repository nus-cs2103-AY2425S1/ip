package axel;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
    public Task getTask(int index) {
        return tasks.get(index);
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public int size() {
        return tasks.size();
    }
}
