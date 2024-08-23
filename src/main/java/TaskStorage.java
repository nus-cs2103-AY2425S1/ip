import java.util.HashMap;
import java.util.Map;

public class TaskStorage {
    private final Map<Integer, Task> tasks;
    private int autoIncrementId;

    public TaskStorage() {
        tasks = new HashMap<>();
        autoIncrementId = 1; // Start the ID from 1
    }

    public void addTask(Task task) {
        tasks.put(autoIncrementId, task);
        autoIncrementId++;
    }

    public void markTask(int taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.markDone();
        } else {
            System.out.println("    Invalid task ID.");
        }
    }

    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public void deleteTask(int taskId) {
        tasks.remove(taskId);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void listTasks() {
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            System.out.println("     " + entry.getKey() + ". " + entry.getValue());
        }
    }
}
