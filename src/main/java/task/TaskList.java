package task;

import exceptions.TaskIndexOutOfBound;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> TaskList) {
        this.tasks = TaskList;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.remove(index);
    }

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

    public Task getTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
