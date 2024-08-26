package utility;

import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public int getTasksSize() {
        return tasks.size();
    }

    public TaskList(Storage storage) throws IOException {
        this.tasks = storage.load();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int index) {
        Task t = tasks.get(index);
        tasks.remove(index);
        return t;
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i).toString());
        }
    }

    public ArrayList<Task> findTasks(String description) {
        ArrayList<Task> findings = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getDescription().contains(description)) {
                findings.add(t);
            }
        }
        return findings;
    }

    public Task markTask(int index) {
        Task t = tasks.get(index);
        t.mark();
        return t;
    }

    public Task unMarkTask(int index) {
        Task t = tasks.get(index);
        t.unMark();
        return t;
    }

    public void save(Storage storage) throws IOException {
        storage.save(tasks);
    }
}
