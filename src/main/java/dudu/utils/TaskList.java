package dudu.utils;

import dudu.task.Task;
import dudu.utils.LineWrapper;

import java.io.FileWriter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int addTask(Task task) throws java.io.IOException {
        tasks.add(task);
        FileWriter fw = new FileWriter("./data/dudu.txt", true);
        fw.write("\n" + task.formatString());
        fw.close();
        String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, tasks.size()));
        System.out.println(output);
        return this.tasks.size();
    }

//    public int getIndex(String[] input) throws dudu.exception.MissingDescriptionException {
//        if (input.length <= 1 || input[1].replaceAll("\\D+", "").isEmpty()) {
//            throw new dudu.exception.MissingDescriptionException("Please input a number");
//        }
//        int index = Integer.parseInt(input[1].replaceAll("\\D+", "")) - 1;
//        if (index < 0 || index >= tasks.size()) {
//            throw new IllegalArgumentException("Please input a valid count");
//        }
//        return index;
//    }

    public Task markTask(int index) {
        tasks.get(index).markCompleted();
        return tasks.get(index);
    }

    public Task unmarkTask(int index) {
        tasks.get(index).markUncompleted();
        return tasks.get(index);
    }

    public Task deleteTask(int index) {
        Task removed = tasks.get(index);
        tasks.remove(index);
        return removed;
    }
}
