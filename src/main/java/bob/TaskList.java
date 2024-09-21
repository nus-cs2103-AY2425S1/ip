package bob;

import bob.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void reset() {
        tasks.clear();
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();

        int i;
        for (i = 0; i < tasks.size() - 1; ++i) {
            text.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        text.append(i + 1).append(".").append(tasks.get(i));

        return text.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
