package sigma.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
