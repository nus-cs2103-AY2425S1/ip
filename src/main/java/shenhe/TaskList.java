package shenhe;

import shenhe.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    public int getSize() {
        return tasks.size();
    }
}
