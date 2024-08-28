package michaelscott.task;

import java.util.ArrayList;
import michaelscott.exception.MichaelScottException;


public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) throws MichaelScottException {
        if (index < 0 || index >= tasks.size()) {
            throw new MichaelScottException("Please provide a task in range");
        }
        tasks.remove(index);
    }

    public Task getTask(int index) throws MichaelScottException {
        if (index < 0 || index >= tasks.size()) {
            throw new MichaelScottException("Please provide a task in range");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void clearList() {
        this.tasks = new ArrayList<Task>();
    }
}
