import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws StreamsException {
        if (index < 0 || index >= tasks.size()) {
            throw new StreamsException("invalid task number");
        }
        tasks.remove(index);
    }

    public Task getTask(int index) throws StreamsException {
        if (index < 0 || index >= tasks.size()) {
            throw new StreamsException("invalid task number");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}