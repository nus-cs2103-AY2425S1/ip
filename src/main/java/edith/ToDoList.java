package edith;

import edith.exception.InvalidTaskNumberException;
import edith.task.Task;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> toDoList = new ArrayList<>();

    public void add(Task task) {
        toDoList.add(task);
        Storage.saveTasks(toDoList);
    }

    public void mark(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        toDoList.get(taskNumber - 1).check();
        Storage.saveTasks(toDoList);
    }

    public void unmark(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        toDoList.get(taskNumber - 1).uncheck();
        Storage.saveTasks(toDoList);
    }

    public String getTask(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        return toDoList.get(taskNumber - 1).toString();
    }

    public int getNumberofTasks() {
        return this.toDoList.size();
    }

    public void delete(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        toDoList.remove(taskNumber - 1);
        Storage.saveTasks(toDoList);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < toDoList.size(); i++) {
            str = str + " " + (i + 1) + "." + toDoList.get(i).toString() + "\n";
        }
        return str;
    }
}
