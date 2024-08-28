package bro.task;

import java.util.ArrayList;

// bro.task.TaskList class encapsulates a task list tracked by bro.Bro Bot
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks () {
        return this.tasks;
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task deleteTask(int taskId) {
        return tasks.remove(taskId);
    }

    public Task markTask(int taskId) throws Exception {
        Task task = tasks.get(taskId);
        task.markTask();
        return task;
    }

    public Task unmarkTask(int taskId) throws Exception {
        Task task = tasks.get(taskId);
        task.unmarkTask();
        return task;
    }

    public void printAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public int getNumberOfTask() {
        return tasks.size();
    }
}
