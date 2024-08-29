import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) throws RubyException {
        if (index < 0 || index >= tasks.size()) {
            throw new RubyException("Invalid task number.");
        }
        tasks.remove(index);
    }

    public Task getTask(int index) throws RubyException {
        if (index < 0 || index >= tasks.size()) {
            throw new RubyException("Invalid task number.");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("     ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}