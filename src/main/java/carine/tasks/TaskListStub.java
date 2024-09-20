package carine.tasks;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import carine.exceptions.InvalidDateException;

/**
 * The `TaskListStub` class is a class created for isolated testing of the parser independent of tasklist.
 */
public class TaskListStub extends TaskList {

    private List<Task> listOfTasks;

    /**
     * Constructs a `TaskListStub` object.
     */
    public TaskListStub() throws IOException, InvalidDateException {
        this.listOfTasks = new ArrayList<>();
    }

    @Override
    public String addTask(Task task) {
        listOfTasks.add(task);
        return "task added";
    }

    public String getTask(int index) {
        return listOfTasks.get(index).toString();
    }

}
