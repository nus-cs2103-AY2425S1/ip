import java.util.HashMap;
import java.util.Map;

public class TaskStorage {
    private final Map<Integer, Task> tasks;
    private final HarddiskStorage harddiskStorage;
    private int autoIncrementId;

    public TaskStorage(HarddiskStorage harddiskStorage) {
        this.harddiskStorage = harddiskStorage;

        // Attempt to load tasks from the hard disk storage
        Map<Integer, Task> loadedTasks = this.harddiskStorage.load();

        // Initialize the tasks map based on the loaded data
        if (loadedTasks != null && !loadedTasks.isEmpty()) {
            tasks = loadedTasks;

            // Find the maximum key to set the autoIncrementId correctly
            autoIncrementId = loadedTasks.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
        } else {
            tasks = new HashMap<>();
            autoIncrementId = 1; // Start the ID from 1
        }
    }

    public void addTask(Task task) {
        tasks.put(autoIncrementId, task);
        this.harddiskStorage.save(tasks);
        autoIncrementId++;
    }

    public void markTask(int taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.markDone();
            this.harddiskStorage.save(tasks);
        } else {
            System.out.println("    Invalid task ID.");
        }
    }

    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public void deleteTask(int taskId) {
        tasks.remove(taskId);
        this.harddiskStorage.save(tasks);
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
