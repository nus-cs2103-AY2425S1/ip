import java.util.ArrayList;

public class TaskStorage {
    private final ArrayList<Task> tasks;

    // Constructor
    public TaskStorage() {
        tasks = new ArrayList<>();
    }

    // Method to add a task
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int taskNumber) {
        //Tasks are 1 indexed but ArrayLists are 0 indexed, thus need to minus 1
        Task t = tasks.get(taskNumber - 1);
        t.markDone();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    // Method to list all tasks
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
        }
    }
}
