package bob;

import bob.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    private final List<Task> TASKS;

    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.TASKS = tasks;
    }

    public boolean isEmpty() {
        return TASKS.isEmpty();
    }

    public void add(Task task) {
        TASKS.add(task);
    }

    public Task remove(int index) {
        return TASKS.remove(index);
    }

    public Task get(int index) {
        return TASKS.get(index);
    }

    public int size() {
        return TASKS.size();
    }

    public void reset() {
        TASKS.clear();
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();

        int i;
        for (i = 0; i < TASKS.size() - 1; ++i) {
            text.append(i + 1).append(".").append(TASKS.get(i)).append("\n");
        }
        text.append(i + 1).append(".").append(TASKS.get(i));

        return text.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return TASKS.iterator();
    }
}
