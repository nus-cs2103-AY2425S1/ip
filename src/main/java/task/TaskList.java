package task;

import exceptions.TaskIndexOutOfBound;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TASKS;
    public TaskList(ArrayList<Task> TaskList) {
        this.TASKS = TaskList;
    }

    public void addTask(Task task) {
        TASKS.add(task);
    }

    public void deleteTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= TASKS.size()) {
            throw new TaskIndexOutOfBound();
        }
        TASKS.remove(index);
    }

    public void markTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= TASKS.size()) {
            throw new TaskIndexOutOfBound();
        }
        TASKS.get(index).setMarkStatus(true);
    }

    public void unmarkTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= TASKS.size()) {
            throw new TaskIndexOutOfBound();
        }
        TASKS.get(index).setMarkStatus(false);
    }

    public Task getTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= TASKS.size()) {
            throw new TaskIndexOutOfBound();
        }
        return TASKS.get(index);
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
    }
}
