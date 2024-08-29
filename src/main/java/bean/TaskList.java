package bean;

import bean.task.Task;

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

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex).completeTask();
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).undoTask();
    }

    public int size() {
        return tasks.size();
    }
}
