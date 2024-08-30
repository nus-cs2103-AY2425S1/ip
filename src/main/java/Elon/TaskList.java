package Elon;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public boolean getIsDone(int index) {
        return this.list.get(index).getIsDone();
    }

    public void markNotDone(int index) {
        this.list.get(index).setNotDone();
    }
    public void markDone(int index) {
        this.list.get(index).setDone();
    }
    public void addTask(Task task) {
        this.list.add(task);
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

    public int listSize() {
        return this.list.size();
    }
}
