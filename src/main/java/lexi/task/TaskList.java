package lexi.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {

    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addTask(Task task, int index) {
        tasks.add(index, task);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void updateTask(Task task, int taskNumber) {
        tasks.remove(taskNumber);
        tasks.add(taskNumber, task);
    }

}
