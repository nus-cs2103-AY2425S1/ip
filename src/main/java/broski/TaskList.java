package broski;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> peek() {
        return list;
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void add(Task task) {
        list.add(task);
    }

    public void remove(int index) {
        list.remove(index);
    }
}
