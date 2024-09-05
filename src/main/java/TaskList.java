import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }
}
