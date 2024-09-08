package BonnieGUI;

import java.util.ArrayList;
import Tasks.*;

public class GUITaskList {
    protected static ArrayList<Task> tasklist = new ArrayList<>();

    public GUITaskList() {
    }

    /**
     * Retrieves the list of tasks.
     * @return The list of tasks.
     */
    protected static ArrayList<Task> getTasks() {
        return tasklist;
    }

    /**
     * Retrieves the size of the task list.
     *
     * @return The size of the task list.
     */
    protected static int getSize() {
        return tasklist.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    protected static void addTask(Task task) {
        tasklist.add(task);
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param taskNumber The number of the task to be marked as done.
     */
    protected static void markTaskAsDone(int taskNumber) {
        tasklist.get(taskNumber - 1).markAsDone();
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskNumber The task number of the task to be unmarked as done.
     */
    protected static void unmarkTaskAsDone(int taskNumber) {
        tasklist.get(taskNumber - 1).unmarkAsDone();
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskNumber The number of the task to be removed.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     */
    protected static void removeTask(int taskNumber) {
        try {
            tasklist.remove(taskNumber - 1);
            System.out.println(String.format("You have successfully deleted task %d!", taskNumber));
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("You cannot delete a task that does not exist!");
        }
    }

    protected static ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasklist) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}