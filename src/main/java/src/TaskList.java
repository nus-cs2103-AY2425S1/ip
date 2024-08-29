package src;

import src.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
