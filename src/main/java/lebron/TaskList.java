package lebron;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) throws LeBronException {
        if (index <= this.tasks.size()) {
            this.tasks.remove(index - 1);
        } else {
            throw new LeBronException("Thats out of bounds bro");
        }
    }

    public void markTask(int index) throws LeBronException {
        if (index <= this.tasks.size()) {
            this.tasks.get(index - 1).markAsDone();
        } else {
            throw new LeBronException("Thats out of bounds bro");
        } 
    }

    public void unmarkTask(int index) throws LeBronException {
        if (index <= this.tasks.size()) {
            this.tasks.get(index - 1).markAsUndone();
        } else {
            throw new LeBronException("Thats out of bounds bro");
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) throws LeBronException {
        if (index <= this.tasks.size()) {
            return this.tasks.get(index - 1);
        } else {
            throw new LeBronException("Thats out of bounds bro");
        }
    }

    
}
