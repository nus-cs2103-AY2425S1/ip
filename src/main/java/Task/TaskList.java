package Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task remove(int index) {
        return this.taskList.remove(index);
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }
}
