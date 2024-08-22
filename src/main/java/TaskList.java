import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ui.TextUI;


import exception.CitadelException;
import exception.CitadelInvalidArgException;
import exception.CitadelInvalidCommandException;
import exception.CitadelTaskNoInput;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task delete(int index) {
        return this.taskList.remove(index - 1);
    }

    public Task get(int index) {
        return this.taskList.get(index - 1);
    }

    public int size() {
        return this.taskList.size();
    }

    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }
}
