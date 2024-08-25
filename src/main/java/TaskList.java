import java.nio.file.Path;
import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasks;
    private Path filePath;

    TaskList(Path filePath) {
        this.tasks = new ArrayList<Task>();
        this.filePath = filePath;
    }

    // add
    void add(Task task) {
        tasks.add(task);
        saveToFile();
    }

    // delete
    String remove(int index) throws IndexOutOfBoundsException {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index. Expected index between 1 and " + tasks.size());
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        saveToFile();
        return "Task deleted: " + task.toString();
    }

    // save to file
    void saveToFile() {
        // save tasks to file
    }

    // list
    String list() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append(tasks.get(i).toString() + '\n');
        }
        if (!str.isEmpty()) str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    String setCompleted(int index, boolean status) throws IndexOutOfBoundsException {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index. Expected index between 1 and " + tasks.size());
        }
        Task task = tasks.get(index);
        task.setCompleted(status);
        saveToFile();
        return "Task marked as" + (status ? "completed" : "incomplete") + ": " + task.toString();
    }

}
