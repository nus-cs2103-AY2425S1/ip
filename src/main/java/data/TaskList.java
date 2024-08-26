package data;

import java.util.ArrayList;

import data.task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task pop(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public String toStringList() {
        if (tasks.size() == 0) {
            return "No tasks in the list!";
        }
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }

    public ArrayList<Task> getArrayList() {
        return tasks;
    }

}
