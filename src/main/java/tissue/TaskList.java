package tissue;

import java.util.ArrayList;

public class TaskList {
    private final String INDENT = "       ";
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task retrieveTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        String parsedText = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            parsedText += INDENT + (i + 1) + "." + " " + task + "\n";
        }
        return parsedText;
    }

    public Task deleteTask(int number) {
        return tasks.remove(number - 1);
    }
}
