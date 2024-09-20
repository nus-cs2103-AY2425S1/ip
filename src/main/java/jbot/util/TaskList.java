package jbot.util;

import java.util.ArrayList;

import jbot.task.Task;

/**
 * A utility class for managing a list of tasks. This class cannot be instantiated.
 */

@SuppressWarnings({"StaticVariableMayNotBeInitialized", "StaticVariableUsedBeforeInitialization"})

public class TaskList {
    private static ArrayList<Task> list;
    private TaskList() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    /**
     * Sets the list of tasks.
     *
     * @param list The new list of tasks to set.
     */
    public static void setList(ArrayList<Task> list) {
        TaskList.list = list;
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public static Task get(int index) {
        return list.get(index);
    }

    /**
     * Retrieves the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public static int size() {
        return list.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add to the list.
     */
    public static void add(Task task) {
        list.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed from the list.
     */
    public static Task remove(int index) {
        return list.remove(index);
    }
}
