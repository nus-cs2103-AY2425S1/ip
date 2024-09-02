package dook.commands;

import java.io.IOException;
import java.util.ArrayList;

import dook.DookException;
import dook.tasks.Task;
import dook.tasks.TaskList;


public class TaskListStub extends TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskListStub(ArrayList<Task> tasks) {
        super(tasks);
    }

    @Override
    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    @Override
    public Task delete(int taskNumber) throws DookException, IOException {
        if (taskNumber < 0 || taskNumber > numOfTasks()) {
            throw new DookException("You don't have that many tasks");
        }

        Task removed = this.tasks.remove(taskNumber);
        return removed;
    }

    @Override
    public int numOfTasks() {
        return this.tasks.size();
    }

    @Override
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
