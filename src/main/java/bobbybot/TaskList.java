package bobbybot;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task markDone(int index) {
        Task task = tasks.get(index);
        task.setIsDone(true);
        return task;
    }

    public Task markUndone(int index) {
        Task task = tasks.get(index);
        task.setIsDone(false);
        return task;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task[] toArray() {
        return tasks.toArray(new Task[0]);
    }
}
