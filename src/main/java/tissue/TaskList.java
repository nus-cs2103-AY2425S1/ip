package tissue;

import java.util.ArrayList;

public class TaskList {
    private final String INDENT = "       ";
    private ArrayList<Task> taskArray = new ArrayList<>();

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
            parsedText += INDENT + (i + 1) + "." + " " + task + "\n";
        }
        return parsedText;
    }

    public Task deleteTask(int number) {
        return taskArray.remove(number - 1);
    }
}
