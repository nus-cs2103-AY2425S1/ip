package lemon;

import lemon.exception.DescriptionException;
import lemon.task.Task;

import java.util.ArrayList;
/**
 * Represents the list of tasks stored
 * @author He Yiheng
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();
    private int numTasks = 0;

    public TaskList() {
        ArrayList<Task> list = new ArrayList<>();
        int numTasks = 0;
    }

    public void addNewTask(Task t) throws DescriptionException {
        if (t.getDescription().isEmpty() || t.getDescription().equals(" "))
            throw new DescriptionException(" OOPS!!! The description of a " + t.getType() + " cannot be empty");

        list.add(t);
        numTasks++;
    }

    public Task deleteTask(int index) {
        Task t = list.remove(index - 1);
        numTasks--;

        return t;
    }

    public int size() {
        return numTasks;
    }

    public Task get(int i) {
        return list.get(i);
    }

    @Override
    public String toString() {
        StringBuilder taskStr = new StringBuilder();
        for (int i = 0; i < numTasks; i++) {
            taskStr.append(" " + (i + 1) + "." + list.get(i).toString() + "\n");
        }
        return taskStr.toString();
    }
}
