import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getCount() {
        return tasks.size();
    }

}
