package Dook.Tasks;

import Dook.DookException;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int numOfTasks() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public Task delete(int taskNumber) throws DookException, IOException {
        if (taskNumber < 0 || taskNumber > numOfTasks()) {
            throw new DookException("You don't have that many tasks");
        }

        Task removed = this.tasks.remove(taskNumber);
        return removed;
    }

    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task getTask(int index) throws DookException{
        if (index > numOfTasks()) {
            throw new DookException("You don't have that many tasks");
        }
        return this.tasks.get(index);
    }


}
