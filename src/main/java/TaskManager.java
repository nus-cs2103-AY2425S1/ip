import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    List<Task> tasks;
    public TaskManager() {
        tasks = new ArrayList<>();
    }
    public String getItem(int index) {
        return index + 1 + ". " + tasks.get(index);
    }
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(getItem(i));
        }
    }
    public void completeTask(int index) {
        Task task = tasks.get(index);
        task.complete();
    }
    public void unCompleteTask(int index) {
        Task task = tasks.get(index);
        task.unComplete();
    }
    public void addTask(String name) {
        tasks.add(new Task(name));
    }
}
