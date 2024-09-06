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

    /**
     * Add a new task to arraylist
     * @param t task that is added into the list
     * @throws DescriptionException Exception when the description of the task is empty
     */
    public boolean addNewTask(Task t) throws DescriptionException {
        if (t.getDescription().isEmpty() || t.getDescription().equals(" ")) {
            throw new DescriptionException(" OOPS!!! The description of a " + t.getType() + " cannot be empty");
        }


        list.add(t);
        numTasks++;

        return true;
    }

    public Task deleteTask(int index) {
        Task t = list.remove(index - 1);
        numTasks--;

        return t;
    }

    public TaskList findTasks(String text) throws DescriptionException {
        TaskList matchingTasks = new TaskList();
        for (Task task : list) {
            String description = task.getDescription();
            if (description.contains(text)) {
                matchingTasks.addNewTask(task);
            }
        }

        return matchingTasks;
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
