package gale.task;

import java.util.ArrayList;

import gale.exception.GaleException;

/**
 * Represents a list of tasks using an ArrayList.
 *
 * @author kaikquah
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a task list with the input ArrayList of Tasks.
     *
     * @param taskList the ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index the index of the task to be deleted, starting from 0
     */
    public Task deleteTask(int index) throws GaleException {
        if (index < 0 || index >= taskList.size()) {
            throw new GaleException("Swoosh! That task number is lost in the wind. Try again?");
        }
        return taskList.remove(index);
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index the index of the task to be retrieved, starting from 0
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     * @return
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns whether the task list is empty as a boolean.
     * @return
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Returns the task list as an ArrayList.
     * @return
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Marks a task as done or not done.
     *
     * @param index the index of the task to be marked, starting from 0
     * @param isDone whether the task is to be marked as done
     */
    public Task markTask(int index, boolean isDone) throws GaleException {
        if (index < 0 || index >= taskList.size()) {
            throw new GaleException("Swoosh! That task number is lost in the wind. Try again?");
        }
        Task task = taskList.get(index);
        if (task.status() == isDone) {
            String message = isDone ? "done." : "not done.";
            throw new GaleException("Oops! This task is already marked as " + message);
        }
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        return task;
    }

    /**
     * Finds tasks that contain the keyword in their description.
     * @param keyword the keyword the user is searching for
     * @return a TaskList containing tasks that contain the keyword
     */
    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : this.taskList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }
}
