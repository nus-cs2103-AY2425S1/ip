package GPT;
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

    public void deleteTask(int index) throws GPTException {
        if (index < 0 || index >= tasks.size()) {
            throw new GPTException("The task number is out of range.");
        }
        tasks.remove(index);
    }

    public void markTask(int index) throws GPTException {
        if (index < 0 || index >= tasks.size()) {
            throw new GPTException("The task number is out of range.");
        }
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) throws GPTException {
        if (index < 0 || index >= tasks.size()) {
            throw new GPTException("The task number is out of range.");
        }
        tasks.get(index).markAsNotDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
