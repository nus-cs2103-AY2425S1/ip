package conversage.task;

import conversage.exception.ConverSageException;

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

    public void add(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws ConverSageException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConverSageException("Invalid task number!");
        }
        tasks.remove(index);
    }

    public Task getTask(int index) throws ConverSageException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConverSageException("Invalid task number!");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }


}
