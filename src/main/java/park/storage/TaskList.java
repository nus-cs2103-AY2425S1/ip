package park.storage;

import park.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public void delete(int i) {
        taskList.remove(i);
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }
}
