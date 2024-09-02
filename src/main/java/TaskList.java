import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private int index;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.index = 0;
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        index = tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        index++;
    }

    public Task deleteTask(int index) {
        Task removedTask = tasks.remove(index);
        index--;
        return removedTask;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markTask(int index) {
        tasks.get(index).complete();
    }

    public void unmarkTask(int index) {
        tasks.get(index).notComplete();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

