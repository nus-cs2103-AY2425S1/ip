package barcus.tasklist;

import barcus.task.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    private int length;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.length = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.length = this.tasks.size();
    }

    public void showTaskList() {
        for (int i = 0; i < this.length; i++) {
            System.out.println(String.valueOf(i + 1) + ". " + tasks.get(i).toString());
//            System.out.println(i);
        }
//        System.out.println(tasks);
    }

    public String convertToSavable() {
        List<String> temp = new ArrayList<>();
        this.tasks.forEach( task -> temp.add(task.convertToSavedString() + "\n"));
        return String.join("", temp);
    }

    public int getLength() {
        return this.length;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        this.length++;
    }

    public String getTaskString(int i) {
        return this.tasks.get(i).toString();
    }

    public void unmarkTask(int i) {
        this.tasks.get(i).unmarkDone();
    }

    public void markTask(int i) {
        this.tasks.get(i).markDone();
    }

    public Task deleteTask(int i) {
        this.length--;
        return this.tasks.remove(i);
    }
}
