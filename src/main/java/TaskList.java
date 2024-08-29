import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public static int getSize() {
        return taskList.size();
    }

    public static Task get(int idx) {
        return taskList.get(idx);
    }
    /**
     * Adds a task to the list and writes the task description to the file.
     *
     * @param input The Task object to add.
     */
    static void add_task(Task input) {
        taskList.add(input);
    }

    /**
     * Deletes a task from both lists by its index.
     *
     * @param idx The index of the task to delete (1-based index).
     */
    public static void delete_task(int idx) {
        if (idx >= 0 && idx <= taskList.size()) {
            Task deleted_task = taskList.remove(idx - 1);
            System.out.println("Noted. I've removed this task:\n" +
                    deleted_task.toString() + "\nNow you have "
                    + taskList.size() + " tasks in the list.\n");
        } else {
            System.out.println("I can't find this task," +
                    " please check which task you want to" +
                    " delete by keying in list!");
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
}
