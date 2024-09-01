import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public TaskList() {
        this.taskArrayList = new ArrayList<Task>();
    }

    @Override
    public String toString() {
        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 1; i <= taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i - 1);
            listStringBuilder.append(task.toString()).append("\n");
        }
        String listString = listStringBuilder.toString();
        return listString;
    }

    public String toStringIndent() {
        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 1; i <= taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i - 1);
            listStringBuilder.append("    ").append(i + ". " + task.toString()).append("\n");
        }
        String listString = listStringBuilder.toString();
        return listString;
    }

    public Task getTask(int index) {
        return this.taskArrayList.get(index);
    }

    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    public Task get(int index) {
        return this.taskArrayList.get(index);
    }

    public void remove(int index) {
        this.taskArrayList.remove(index);
    }
}
