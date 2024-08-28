package astra;

import java.util.ArrayList;
import java.util.Collection;

import astra.task.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(Collection<? extends Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public String toText() {
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            text.append(task.toText()).append('\n');
        }
        return text.toString();
    }

    @Override
    public String toString() {
        int len = tasks.size();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < len; i++) {
            text.append(String.format("%d. %s \n", i + 1, tasks.get(i)));
        }
        return text.toString();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) throws AstraException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    public Task delete(int index) throws AstraException {
        try {
            Task t = this.get(index);
            tasks.remove(index - 1);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    public Task markAsDone(int index, boolean done) throws AstraException {
        try {
            Task t = this.get(index);
            t.setDone(done);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    public int length() {
        return tasks.size();
    }

}
