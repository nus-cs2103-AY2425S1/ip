package task;

import exceptions.TaskIndexOutOfBound;

import java.util.ArrayList;

public class TaskListStub extends TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private boolean markCalled = false;

    public TaskListStub(ArrayList<Task> TaskList) {
        super(TaskList);
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void deleteTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.remove(index);
    }

    @Override
    public void markTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.get(index).setMarkStatus(true);
        markCalled = true;
    }

    @Override
    public Task getTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        return tasks.get(index);
    }

    @Override
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
