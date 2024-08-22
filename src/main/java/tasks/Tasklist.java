package tasks;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void markAsUndone(int index) {
        taskList.get(index).unmarkAsDone();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public int listLength() {
        return taskList.size();
    }

    @Override
    public String toString() {
        // suggested by Copilot
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}