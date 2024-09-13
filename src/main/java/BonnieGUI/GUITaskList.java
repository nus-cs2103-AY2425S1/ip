package BonnieGUI;

import java.util.ArrayList;
import Tasks.*;

public class GUITaskList {
    protected static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Retrieves the list of tasks.
     * @return The list of tasks.
     */
    protected static ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Retrieves the size of the task list.
     *
     * @return The size of the task list.
     */
    protected static int getSize() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    protected static void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param taskNumber The number of the task to be marked as done.
     */
    protected static void markTaskAsDone(int taskNumber) {
        taskList.get(taskNumber - 1).markAsDone();
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskNumber The task number of the task to be unmarked as done.
     */
    protected static void unmarkTaskAsDone(int taskNumber) {
        taskList.get(taskNumber - 1).unmarkAsDone();
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskNumber The number of the task to be removed.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     */
    protected static void removeTask(int taskNumber) {
        try {
            taskList.remove(taskNumber - 1);
            System.out.println(String.format("You have successfully deleted task %d!", taskNumber));
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("You cannot delete a task that does not exist!");
        }
    }

    /**
     * Finds tasks that contain a specified keyword.
     *
     * @param keyword The keyword to search for in the tasks.
     * @return An ArrayList of tasks that contain the keyword.
     */
    protected static ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}