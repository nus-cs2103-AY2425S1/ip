import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    // Call this method after any operation that modifies the task list
    public void addTask(Task task) {
        tasks.add(task);
        Storage.saveTasksToFile(tasks); // Save after adding a task
    }

    public Task removeTask(int index) {
        Task removedTask = tasks.remove(index);
        Storage.saveTasksToFile(tasks); // Save after removing a task
        return removedTask;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
