package bobby.ui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manages a list of tasks, providing methods to add, delete, retrieve, and print tasks.
 * The class is designed to handle a list of {@link Task} objects and interact with them.
 */
public class TaskList {

    /**
     * An ArrayList to store task
     */
    private static ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The size of the task list.
     */
    public static int getSize() {
        return taskList.size();
    }
    /**
     * Retrieves a task from the list by its index.
     *
     * @param idx The index of the task to retrieve (0-based index).
     * @return The {@link Task} object at the specified index.
     */
    public static Task get(int idx) {
        return taskList.get(idx);
    }

    /**
     * Adds a task to the list.
     *
     * @param input The {@link Task} object to add.
     */
    static void add_task(Task input) {
        taskList.add(input);
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param idx The index of the task to delete (1-based index).
     * @return A string message indicating the result of the deletion operation.
     */
    public static String deleteTask(int idx) {
        String response;
        if (idx >= 0 && idx <= taskList.size()) {
            Task deletedtask = taskList.remove(idx - 1);
            response = "Noted. I've removed this task:\n"
                    + deletedtask.toString() + "\nNow you have "
                    + taskList.size() + " tasks in the list.\n";
        } else {
            response = "I can't find this task,"
                    + " please check which task you want to"
                    + " delete by keying in list!";
        }
        return response;
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param idx The index of the task to retrieve (1-based index).
     * @return The {@link Task} object at the specified index,
     *      or null if the index is invalid.
     */
    public static Task retrive_task(int idx) {
        if (idx >= 0 && idx <= taskList.size()) {
            return taskList.get(idx - 1);
        }
        return null;
    }

    /**
     * Prints all tasks in the list for chat display.
     *
     * @return A string representation of the tasks in the list, each on a new line.
     */
    public static String print_list() {
        String response = "Here are the tasks in your list:\n";
        for (int x = 0; x < taskList.size(); x++) {
            response = response + (x + 1) + "." + taskList.get(x).toString() + "\n";
        }
        return response;
    }

    /**
     * Searches for tasks containing the specified keyword as a whole word in their description.
     *
     * @param keyword The whole word to search for in task descriptions.
     * @return A string containing the tasks that match the search keyword as a whole word,
     *      or a message if no matches are found.
     */
    public static String findTask(String keyword) {
        String response;
        ArrayList<Task> matchingTasks = new ArrayList<>();

        String patternString = "\\b" + Pattern.quote(keyword) + "\\b";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);

        for (Task task : taskList) {
            Matcher matcher = pattern.matcher(task.toString());
            if (matcher.find()) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            response = "No matching tasks found.";
        } else {
            response = "Here are the matching tasks in your list:\n";
            for (int x = 0; x < matchingTasks.size(); x++) {
                response = response + (x + 1) + "." + matchingTasks.get(x).toString() + "\n";
            }
        }
        return response;
    }
}
