package orion.chatbot;

import orion.exceptions.OrionInputException;
import orion.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;
    private int noTasks;

    protected TaskList() {
        tasks = new ArrayList<>();
        noTasks = 0;
    }

    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
        noTasks = tasks.size();
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
        return noTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        noTasks++;
    }

    public Task markTask(int taskNo) throws OrionInputException {
        Task task = tasks.get(taskNo);
        task.setDone();
        return task;
    }

    public Task unmarkTask(int taskNo) throws OrionInputException {
        Task task = tasks.get(taskNo);
        task.setUndone();
        return task;
    }

    public Task deleteTask(int taskNo) {
        Task task = tasks.get(taskNo);
        tasks.remove(task);
        noTasks--;
        return task;
    }

    public List<String> getSavedTaskDescriptions() {
        List<String> savedTaskDescriptions = new ArrayList<>();
        for (Task task : tasks) {
            savedTaskDescriptions.add(task.saveString() + "\n");
        }
        return savedTaskDescriptions;
    }
}
