import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public void addTask(Task task) throws TarsException {
        tasks.add(task);
        saveTasks();
    }

    public void removeTask(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("The specified task number is out of bounds.");
        }
        tasks.remove(index);
        saveTasks();
    }

    private void saveTasks() throws TarsException {
        try {
            storage.saveTasks(this.tasks);
        } catch (TarsException e) {
            System.out.println("Error saving task: "+ e.getMessage());
        }
    }

    public Task markTaskDone(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("The specified task number is out of bounds.");
        }
        Task task = tasks.get(index);
        task.setDone();
        saveTasks();
        return task;
    }

    public Task markTaskUndone(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("The specified task number is out of bounds.");
        }
        Task task = tasks.get(index);
        task.setUndone();
        saveTasks();
        return task;
    }

    public String listTasks() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return result.toString().trim();
    }

    public int getSize() {
        return tasks.size();
    }

}
