import java.util.ArrayList;

public class TextStorage {
    private final ArrayList<String> tasks;

    // Constructor
    public TextStorage() {
        tasks = new ArrayList<>();
    }

    // Method to add a task
    public void addTask(String task) {
        tasks.add(task);
    }

    // Method to list all tasks
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
        }
    }
}
