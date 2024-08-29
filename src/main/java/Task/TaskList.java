package Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmark(int index) {
        tasks.get(index).markAsNotDone();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "No tasks in list!";
        }
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += String.format("%d.%s", i + 1, tasks.get(i));
            if (i < tasks.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }
}
