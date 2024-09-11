package lemon;

import java.util.ArrayList;

import lemon.exception.DescriptionException;
import lemon.task.Task;
/**
 * Represents the list of tasks stored
 * @author He Yiheng
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();
    private int numTasks = 0;

    /**
     * Constructor for TaskList
     */
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

    /**
     * Remove a task from the arraylist
     * @param i index of the task within the list
     * @return task that is removed
     */
    public Task deleteTask(int i) {
        Task t = list.remove(i - 1);
        numTasks--;

        return t;
    }

    /**
     * Find tasks from the list with description of matching keyword
     * @param text keyword to find
     * @return TaskList that contain tasks with matching keyword
     * @throws DescriptionException Error when adding a task with empty description
     */
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

    /**
     * Returns the total number of tasks
     * @return number of tasks
     */
    public int size() {
        return numTasks;
    }

    /**
     * Returns the task within the list given its index
     * @param i index of the task within the list
     * @return task with the corresponding index
     */
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
