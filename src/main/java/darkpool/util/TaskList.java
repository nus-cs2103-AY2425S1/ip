package darkpool.util;

import darkpool.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 * It provides methods to add, delete, mark, and unmark tasks,
 * as well as to retrieve the size of the list and convert it to a string.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns the string representation of the task at the specified index.
     *
     * @param index the index of the task
     * @return the string representation of the task
     */
    private String getTaskString(int index) {
        return this.taskList.get(index).toString();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task
     * @return the task at the specified index
     */
    private Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes the task at the specified index and returns its string representation.
     *
     * @param index the index of the task to be deleted
     * @return the string representation of the deleted task
     */
    public String deleteTask(int index) {
        Task task = getTask(index);
        this.taskList.remove(index);
        return task.toString();
    }

    /**
     * Marks the task at the specified index as done and returns its string representation.
     *
     * @param index the index of the task to be marked as done
     * @return the string representation of the marked task
     */
    public String markTask(int index) {
        getTask(index).markDone();
        return getTaskString(index);
    }

    /**
     * Unmarks the task at the specified index and returns its string representation.
     *
     * @param index the index of the task to be unmarked
     * @return the string representation of the unmarked task
     */
    public String unmarkTask(int index) {
        getTask(index).markUndone();
        return getTaskString(index);
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return the string representation of the task list
     */
    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "\tbozo you got no tasks";
        }
        StringBuilder temp = new StringBuilder("\twhy am i here\n\t\t");
        for (int i = 0; i < this.taskList.size(); i++) {
            temp.append((i + 1)).append(". ").append(getTaskString(i)).append("\n\t\t");
        }
        temp.setLength(temp.length() - 3);
        return String.valueOf(temp);
    }

    /**
     * Returns the string representation of the task list suitable for file storage.
     *
     * @return the string representation of the task list for file storage
     */
    public String toFileString() {
        StringBuilder fileString = new StringBuilder();
        for (Task task : taskList) {
            fileString.append(task.toFileString());
        }
        return String.valueOf(fileString);
    }

}