package chatgpt.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public TaskList find(String keyword) {
        ArrayList<Task> res = tasks.stream()
                .filter(task -> task
                        .toString()
                        .contains(keyword))
                .collect(Collectors
                        .toCollection(ArrayList::new));
        return new TaskList(res);
    }
}
