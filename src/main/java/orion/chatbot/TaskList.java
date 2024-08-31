package orion.chatbot;

import orion.exceptions.OrionInputException;
import orion.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;
    private int noTasks;

    protected TaskList() {
        this.tasks = new ArrayList<>();
        this.noTasks = 0;
    }

    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.noTasks = tasks.size();
    }

    public List<String> getTaskDescriptions() {
        List<String> taskDescriptions = new ArrayList<>();
        for (int i = 0; i < noTasks; i++) {
            String task = String.format("%d. %s", i + 1, tasks.get(i));
            taskDescriptions.add(task);
        }
        return taskDescriptions;
    }

    public int getNoTasks() {
        return this.noTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        noTasks++;
    }

    public Task markTask(int taskNo) throws OrionInputException {
        Task task = this.tasks.get(taskNo);
        task.setDone();
        return task;
    }

    public Task unmarkTask(int taskNo) throws OrionInputException {
        Task task = this.tasks.get(taskNo);
        task.setUndone();
        return task;
    }

    public Task deleteTask(int taskNo) {
        Task task = this.tasks.get(taskNo);
        this.tasks.remove(task);
        this.noTasks--;
        return task;
    }

    public List<String> getSavedTaskDescriptions() {
        List<String> savedTaskDescriptions = new ArrayList<>();
        for (Task task : this.tasks) {
            savedTaskDescriptions.add(task.saveString() + "\n");
        }
        return savedTaskDescriptions;
    }
}
