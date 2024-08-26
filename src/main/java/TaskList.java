import Task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        Storage.saveTasksToLocalDisk(tasks);
    }

    public Task deleteTask(int index){
        Task removedTask = tasks.remove(index);
        Storage.saveTasksToLocalDisk(tasks);
        return removedTask;
    }

    @Override
    public String toString() {
        return null;
    }
}
