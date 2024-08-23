import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    public TaskManager() {
        this.tasks = new ArrayList<Task>();
    }

    public List<Task> getTasks() {
        // in the future return a view of the list
        // rather than the list to avoid errors
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addTask(String task) {
        tasks.add(new Task(task));
    }

}
