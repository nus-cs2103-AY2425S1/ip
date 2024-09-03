package zero.task;

import java.util.ArrayList;

import zero.exception.ZeroException;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws ZeroException {
        validateIndex(index);
        Task item = tasks.get(index);
        tasks.remove(index);
        return item;
    }

    public Task getTask(int index) throws ZeroException {
        validateIndex(index);
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    private void validateIndex(int index) throws ZeroException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZeroException("The task number is out of range.");
        }
    }
}
