package tasks;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TaskList;
    
    public TaskList() {
        this.TaskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        TaskList.add(task);
    }

    public void markAsDone(int index) {
        TaskList.get(index).markAsDone();
    }

    public void markAsUndone(int index) {
        TaskList.get(index).unmarkAsDone();
    }

    public Task getTask(int index) {
        return TaskList.get(index);
    }

    public int listLength() {
        return TaskList.size();
    }

    @Override
    public String toString() {
        // suggested by Copilot
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TaskList.size(); i++) {
            sb.append((i + 1)).append(". ").append(TaskList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}