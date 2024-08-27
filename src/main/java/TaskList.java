import java.util.ArrayList;
import java.util.List;

public class TaskList {
    static List<Task> tasks = new ArrayList<>(100);

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);
    }
}