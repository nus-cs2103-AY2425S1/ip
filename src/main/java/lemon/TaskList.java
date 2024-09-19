package lemon;

import java.util.ArrayList;

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
        this.list = new ArrayList<>();
        this.numTasks = 0;
    }

    /**
     * Add a new task to arraylist
     * @param t task that is added into the list
     */
    public boolean addNewTask(Task t) {
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
     */
    public TaskList findTasks(String text) {
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
        return list.get(i - 1);
    }

    @Override
    public String toString() {
        StringBuilder taskStr = new StringBuilder();
        for (int i = 0; i < numTasks; i++) {
            taskStr.append(" ").append(i + 1).append(".").append(list.get(i).toString()).append("\n");
        }
        return taskStr.toString();
    }
}
