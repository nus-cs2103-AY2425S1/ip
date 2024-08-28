import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArray = new ArrayList<>();
    private final String INDENT = "       ";

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public Task retrieveTask(int index) {
        return taskArray.get(index);
    }

    public int size() {
        return taskArray.size();
    }

    public void add(Task task) {
        taskArray.add(task);
    }

    @Override
    public String toString() {
        String parsedText = "";
        for (int i = 0; i < taskArray.size(); i++) {
            Task task = taskArray.get(i);
            parsedText += INDENT + String.valueOf(i + 1) + "." + " " + task + "\n";
        }
        return parsedText;
    }

    public Task deleteTask(int number) {
        return taskArray.remove(number - 1);

    }
}
