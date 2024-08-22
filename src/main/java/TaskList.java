import java.util.ArrayList;

// TaskList class encapsulates a task list tracked by Bro Bot
public class TaskList {
    private ArrayList<String> tasks;

    public TaskList() {
        this.tasks = new ArrayList<String>();
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void printAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
