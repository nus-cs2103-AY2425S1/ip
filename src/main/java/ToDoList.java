import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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
