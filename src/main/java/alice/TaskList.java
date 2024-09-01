package alice;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Manages the tasks, including adding, marking as done/undone, deleting, and retrieving tasks.
 */
public class TaskList {
    private static final String line =
            "____________________________________________________________";
    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Constructs a TaskList with an empty list of tasks and a new Ui instance.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Constructs a TaskList with the specified list of tasks and a new Ui instance.
     *
     * @param list An ArrayList of Task objects to initialize the task list with.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.ui = new Ui();
    }

    /**
     * Adds a task to the list and informs the user of the addition.
     *
     * @param task The task to be added to the list.
     */
    public void addToList(Task task) {
        try {
            this.list.add(task);
            ui.addToListMsg(task, this.list.size());
        } catch (Exception e) {
            ui.addFailMsg();
        }
    }

    /**
     * Marks a task in the list as done.
     *
     * @param num The index of the task to be marked as done.
     */
    public void markTask(int num) {
        if (num <= list.size() && num > 0) {
            Task currTask = list.get(num - 1);
            currTask.markDone();
            ui.markMsg(currTask);
        } else {
            ui.invalidNumMsg();
        }
    }

    /**
     * Marks a task in the list as undone.
     *
     * @param num The index of the task to be marked as undone.
     */
    public void unmarkTask(int num) {
        if (num <= list.size() && num > 0) {
            Task currTask = list.get(num - 1);
            currTask.markUndone();
            ui.unMarkMsg(currTask);
        } else {
            ui.invalidNumMsg();
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param num The index of the task to be deleted.
     */
    public void delete(int num) {
        if (num <= list.size() && num > 0) {
            Task currTask = list.get(num - 1);
            this.list.remove(currTask);
            ui.deleteMsg(currTask, list.size());
        } else {
            ui.invalidNumMsg();
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getTask() {
        return this.list;
    }

    public int getSize() {
        return this.list.size();
    }

    /**
     * Finds tasks in the list that contain the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A TaskList containing tasks that match the keyword.
     */
    public TaskList findTask(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : list) {
            if (task.description.contains(keyword)) {
                temp.add(task);
            }
        }
        return new TaskList(temp);
    }

    /**
     * Returns a string representation of the task list.
     * Each task is listed with its index and description.
     *
     * @return A string representing the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            int currNum = i + 1;
            sb.append(currNum).append(".").append(currTask.toString()).append("\n");
        }
        return sb.toString();
    }
}
