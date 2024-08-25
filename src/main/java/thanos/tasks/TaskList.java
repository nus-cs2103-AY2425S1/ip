package thanos.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import thanos.storage.Storage;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskList(Storage storage) {
        this.tasks = storage.load();
        this.storage = storage;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task mark(int index) {
        Task task = this.tasks.get(index);
        task.setDone(true);
        this.storage.save(this.tasks);
        return task;
    }

    public Task unmark(int index) {
        Task task = this.tasks.get(index);
        task.setDone(false);
        this.storage.save(this.tasks);
        return task;
    }

    public Task add(Task task) {
        this.tasks.add(task);
        this.storage.save(this.tasks);
        return task;
    }

    public Task remove(int index) {
        Task task = this.tasks.remove(index);
        this.storage.save(this.tasks);
        return task;

    }

    public ArrayList<Task> findByDate(LocalDateTime date) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.checkDate(date)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
