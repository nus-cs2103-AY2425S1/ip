package task;

import exceptions.TaskIndexOutOfBound;

import java.util.ArrayList;

public class TaskListStub extends TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

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
    }

    public void unmarkTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.get(index).setMarkStatus(false);
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
