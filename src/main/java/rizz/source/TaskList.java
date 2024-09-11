package rizz.source;
import java.util.ArrayList;
import rizz.tasks.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }


    public String[] export() {
        String[] exportTasks = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            exportTasks[i] = tasks.get(i).export();
        }
        return exportTasks;
    }
    
}