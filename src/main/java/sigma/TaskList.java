package sigma;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sigma.task.Task;
/**
 * Class that wraps around the list of Tasks with added operations
 *
 * @author Qiao Yi
 */
public class TaskList {
    private static List<Task> items;

    /**
     * Constructor for a TaskList
     */
    public TaskList() {
        items = new ArrayList<>();
    }

    /**
     * Returns the list of tasks
     *
     * @return The list of tasks
     */
    public static List<Task> getItems() {
        return items;
    }

    /**
     * Retrieves the task at the specified index
     *
     * @param i The index of the task
     * @return The task at the index specified
     */
    public static Task get(int i) {
        return items.get(i);
    }

    /**
     * Returns the list of tasks as a friendly formatted String
     *
     * @return String representation of the tasks
     */
    public static String toPrettyList() {
        if (items.isEmpty()) {
            return "No tasks saved, add one now";
        }
        int i = 1;
        StringBuilder result = new StringBuilder(); // this is a terrible time complexity
        for (Task item : items) {
            result.append("\n").append(i).append(". ").append(item);
            i++;
        }
        return result.toString();
    }



    /**
     * Adds the given task to the TaskList
     *
     * @param task The task to be added
     */
    public static void addToList(Task task) {
        items.add(task);
    }

    /**
     * Retrieves the current size of the TaskList
     *
     * @return The size of the TaskList
     */
    public static int getSize() {
        return items.size();
    }

    /**
     * Removes the specified task from the list
     *
     * @param task The task to be removed
     */
    public static void remove(Task task) {
        items.remove(task);
    }
}
