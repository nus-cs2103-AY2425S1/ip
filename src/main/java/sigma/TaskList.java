package sigma;
import sigma.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @return The list of tasks
     */
    public static List<Task> getItems() {
        return items;
    }

    /**
     * Retrieves the task at the specified index
     * @param i The index of the task
     * @return The task at the index specified
     */
    public static Task get(int i) {
        return items.get(i);
    }

    /**
     * Returns the list of tasks as a friendly formatted String
     * @return String representation of the tasks
     */
    public static String toPrettyList() {
        int i = 1;
        StringBuilder result = new StringBuilder(); // this is a terrible time complexity
        for (Task item : items) {
            result.append("\n").append(i).append(". ").append(item.toString());
            i++;
        }
        return result.toString();
    }

    /**
     * Deletes the task that the user specifies
     * @param userInput The task that the user wants to delete
     */
    public void handleDelete(String userInput) {
        Pattern pattern = Pattern.compile("(delete) (\\d+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            int taskNumber = Integer.parseInt(matcher.group(2)) - 1;
            if (taskNumber >= 0 && taskNumber < items.size()) {
                Task task = items.get(taskNumber);
                items.remove(task);
                System.out.println("task removed:\n" + task.toString() + "\nNow you have " + items.size() + " tasks in the list");
            }
        }
    }

    /**
     * Adds the given task to the TaskList
     * @param task The task to be added
     */
    public static void addToList(Task task) {
        items.add(task);
    }

    /**
     * Retrieves the current size of the TaskList
     * @return The size of the TaskList
     */
    public static int getSize() {
        return items.size();
    }
}
