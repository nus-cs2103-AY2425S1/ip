package tasks;
import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> taskList;
    
    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
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