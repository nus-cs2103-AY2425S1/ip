package revir.tasks;
import java.io.IOException;
import revir.system.Storage;
import revir.user.Ui;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage, Ui ui) {
        this.tasks = new ArrayList<Task>();
        this.storage = storage;
        try {
            this.tasks = storage.loadFromFile();
        } catch (Exception e) {
            tasks = new ArrayList<Task>();
            ui.showError("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    public void add(Task task) throws IOException {
        tasks.add(task);
        storage.saveToFile(this.tasks);
    }

    public String remove(int index) throws IndexOutOfBoundsException, IOException {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index. Expected index between 1 and " + tasks.size());
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        storage.saveToFile(this.tasks);
        return "Task deleted: " + task.toString();
    }

    public String list() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append(tasks.get(i).toString() + '\n');
        }
        if (!str.isEmpty()) str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    public String find(String keyword) {
        StringBuilder str = new StringBuilder();
        tasks.forEach(task -> {
            if (task.toString().contains(keyword)) {
                str.append(task.toString() + '\n');
            }
        });
        if (!str.isEmpty()) str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    public String setCompleted(int index, boolean status) throws IndexOutOfBoundsException, IOException {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index. Expected index between 1 and " + tasks.size());
        }
        Task task = tasks.get(index);
        task.setCompleted(status);
        storage.saveToFile(this.tasks);
        return "Task marked as" + (status ? "completed" : "incomplete") + ": " + task.toString();
    }

}
