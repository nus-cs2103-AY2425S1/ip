package oyster.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void insert(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task pop(int index) {
        return tasks.remove(index);
    }

    public void mark(int index) {
        tasks.get(index).mark();
    }

    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int length() {
        return tasks.size();
    }

    public ArrayList<Task> getItems() {
        @SuppressWarnings("unchecked")
        ArrayList<Task> copy = (ArrayList<Task>) tasks.clone();
        return copy;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result += String.format("%d. %s",i + 1 , tasks.get(i)) + (i < tasks.size() - 1 ? "\n" : "");
        }

        return result;
    }
}
