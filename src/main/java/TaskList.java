import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) throws ShoAIException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShoAIException("Task number out of range.");
        }
        return tasks.get(index);
    }

    public Task removeTask(int index) throws ShoAIException {
        if (index < 0 || index >= tasks.size()) {
            throw new ShoAIException("Task number out of range.");
        }
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}
