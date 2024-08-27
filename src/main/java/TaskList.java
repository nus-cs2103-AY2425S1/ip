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

    public void deleteTask(int i) {
        this.list.remove(i);
    }

    public Task getTask(int i) {
        return this.list.get(i);
    }

    public void refreshTaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getAllTask() {
        return this.list;
    }

}
