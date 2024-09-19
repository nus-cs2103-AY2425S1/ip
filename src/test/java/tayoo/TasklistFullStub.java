package tayoo;

import tayoo.tasks.Task;
import tayoo.tasks.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TasklistFullStub extends Tasklist{

    public TasklistFullStub(List<Task> tasklist) {
        super(tasklist);
    }

    public TasklistFullStub() {
        super(new ArrayList<Task>());
    }

    @Override
    public boolean addTask(Task task) {
        return false;
    }

    @Override
    public Task deleteTask(int taskNumber) {
        return new ToDo("Return book", true);
    }

    @Override
    public String printTaskList() {
        return "Here are the tasks in your list: \n" +
                "1. [X] Return book\n";
    }

    @Override
    public boolean deleteAll() {
        return true;
    }

    @Override
    public boolean markTask(int taskNumber) {
        if (taskNumber == 5) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean unmarkTask(int taskNumber) {
        if (taskNumber == 5) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getSize() {
        return 100;
    }

    @Override
    public String getTaskStr(int taskNumber) {
        if (taskNumber == 5) {
            return "Return book";
        } else if (taskNumber == 4){
            return "Read book";
        } else {
            return null;
        }
    }

    @Override
    public String find(String substring) {
        if (substring.equalsIgnoreCase("book")) {
            return "Return Book";
        } else {
            return "";
        }
    }

    @Override
    public String numberOfTasksLeft() {
        return "\n Now you have 100 tasks in your list";
    }

}
