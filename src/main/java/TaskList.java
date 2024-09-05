import java.util.List;
import java.util.ArrayList;

public class TaskList {
    // Change tasks to be a List<Task> instead of ArrayList<Task>
    private final List<Task> tasks;

    // Constructor that accepts any List<Task>
    public TaskList(List<Task> tasks) {
        this.tasks = tasks; // Use a general List<Task>
    }

    // Default constructor initializes an empty ArrayList
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }
}
