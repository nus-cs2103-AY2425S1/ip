package bobby.ui;

import java.util.ArrayList;

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
     * Deletes a task from both lists by its index.
     *
     * @param idx The index of the task to delete (1-based index).
     */
    public static void deleteTask(int idx) {
        if (idx >= 0 && idx <= taskList.size()) {
            Task deletedtask = taskList.remove(idx - 1);
            System.out.println("Noted. I've removed this task:\n"
                    + deletedtask.toString() + "\nNow you have "
                    + taskList.size() + " tasks in the list.\n");
        } else {
            System.out.println("I can't find this task,"
                    + " please check which task you want to"
                    + " delete by keying in list!");
        }
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param idx The index of the task to retrieve (1-based index).
     * @return The Task object at the specified index, or null if not found.
     */
    public static Task retrive_task(int idx) {
        if (idx >= 0 && idx <= taskList.size()) {
            return taskList.get(idx - 1);
        }
        return null;
    }

    /**
     * Prints all tasks in the list, each on a new line.
     * User-friendly mode.
     */
    public static void print_list() {
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < taskList.size(); x++) {
            System.out.println(x + 1 + "." + taskList.get(x).toString());
        }
        System.out.println();
    }

    /**
     * Searches for tasks containing the specified keyword in their description.
     *
     * @param keyword The substring to search for in task descriptions.
     */
    public static void findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int x = 0; x < matchingTasks.size(); x++) {
                System.out.println(x + 1 + "." + matchingTasks.get(x).toString());
            }
        }
    }

}
