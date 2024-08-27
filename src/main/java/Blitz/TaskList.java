package Blitz;

/* My import */
import Task.Task;

/* System import */
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public int size() {
        return this.list.size();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task deleteTask(int i) {
        return this.list.remove(i);
    }

    public Task getTask(int i) {
        return this.list.get(i);
    }

    public ArrayList<Task> getAllTask() {
        return this.list;
    }

}
