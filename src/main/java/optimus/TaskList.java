package optimus;

import optimus.exceptions.InvalidTaskNumberException;
import optimus.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> loadedFromStorage) {
        this.list = loadedFromStorage;
    }

    public Task getTask(int taskNum) throws InvalidTaskNumberException {
        if (isInvalidTaskNum(taskNum)) {
            throw new InvalidTaskNumberException();
        } else {
            return list.get(taskNum);
        }
    }

    public int getNumOfTasks() {
        return list.size();
    }

    public Task removeTask(int taskNum) throws InvalidTaskNumberException {
        if (isInvalidTaskNum(taskNum)) {
            throw new InvalidTaskNumberException();
        } else {
            return list.remove(taskNum);
        }
    }

    public void addTask(Task task) {
        list.add(task);
    }
    private boolean isInvalidTaskNum(int taskNum) {
        return taskNum < 0 || taskNum >= list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
