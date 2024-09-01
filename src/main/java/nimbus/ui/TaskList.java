package nimbus.ui;

import nimbus.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private static String horizontalLine = "\n-------------------------------------------------";

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }
    public void delete(Task task) {
        this.taskList.remove(task);
    }

    @Override
    public String toString() {
        String output = "Nimbus.Nimbus says this is your list: \n";
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                output += (String.valueOf(i + 1) + ". " + taskList.get(i));
            } else {
                output += (String.valueOf(i + 1) + ". " + taskList.get(i) + "\n");
            }
        }
        output += horizontalLine;
        System.out.println(output);
        return output;
    }
}