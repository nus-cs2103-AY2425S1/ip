import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    // Call this method after any operation that modifies the task list
    public void addTask(Task task) {
        tasks.add(task);
        // to save after adding a task
    }

    public Task removeTask(int index) {
        // to save after removing a task
        return tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }
}
