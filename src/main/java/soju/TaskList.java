package soju;

import soju.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public Task deleteTask(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }
    public Task markTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }
    public Task unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.unmark();
        return task;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public int size() {
        return tasks.size();
    }
    @Override
    public String toString() {
        return tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + "." + task)
                .collect(Collectors.joining("\n"));
    }
}
