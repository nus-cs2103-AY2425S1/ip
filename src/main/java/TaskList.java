import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    @Override
    public String toString() {
        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            listStringBuilder.append(task.toString()).append("\n");
        }
        String listString = listStringBuilder.toString();
        return listString;
    }

    public String toStringIndent() {
        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            listStringBuilder.append("    ").append(task.toString()).append("\n");
        }
        String listString = listStringBuilder.toString();
        return listString;
    }
}
