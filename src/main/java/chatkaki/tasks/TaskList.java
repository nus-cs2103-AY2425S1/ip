package chatkaki.tasks;

import java.util.ArrayList;
import chatkaki.Ui;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @param fromStorage Whether the task is being added from storage.
     */
    public static void addTask(Task task, boolean fromStorage) {
        tasks.add(task);
        if (!fromStorage) {
            Ui.printMessage("Got it. I've added this task:\n " + task + "\n Now you have " + TaskList.getSize() +
                    " task" + (TaskList.getSize() == 1 ? "" : "s") + " in the list.");
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public static Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets a task from the task list.
     *
     * @param index The index of the task to get.
     * @return The task at the specified index.
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public static int getSize() {
        return tasks.size();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }
}
