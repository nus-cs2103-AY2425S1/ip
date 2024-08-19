import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructs a TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    /**
     * Marks a specific task as done based on the task number.
     *
     * @param taskNumber The task number to mark as done.
     * @throws OllieException If the task number is invalid.
     */
    public void markTaskAsDone(int taskNumber) throws OllieException {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsDone(true);
            messageWrapper("Nice! ☺ I've marked this task as done ☺ :\n  " + tasks.get(taskNumber));
        } else {
            throw new OllieException("Please enter a valid task number within the list ☺");
        }
    }

    /**
     * Unmarks a specific task as not done based on the task number.
     *
     * @param taskNumber The task number to unmark as done.
     * @throws OllieException If the task number is invalid.
     */
    public void unmarkTaskAsDone(int taskNumber) throws OllieException {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsDone(false);
            messageWrapper("OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber));
        } else {
            throw new OllieException("Please enter a valid task number within the list ☺");
        }
    }

    /**
     * Lists all the tasks along with their statuses.
     */
    public void listTasks() {
        String border = "--".repeat(30);
        System.out.println(border);
        System.out.println("Here are the tasks in your list ☺ :");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(border);
    }

    /**
     * Adds a new task to the list and prints the formatted output.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount = tasks.size();
        printTaskAdded(task);
    }

    /**
     * Prints the formatted message when a task is added.
     *
     * @param task The task that was added.
     */
    private void printTaskAdded(Task task) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println("Got it. I've added this task ☺:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list. ☺");
        System.out.println(border);
    }

    /**
     * Deletes a specific task based on the task number.
     *
     * @param taskNumber The task number to delete.
     * @throws OllieException If the task number is invalid.
     */
    public void deleteTask(int taskNumber) throws OllieException {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            taskCount = tasks.size();
            messageWrapper("Noted. I've removed this task:\n  " + removedTask +
                    "\nNow you have " + taskCount + " tasks in the list.");
        } else {
            throw new OllieException("Please enter a valid task number within the list ☺");
        }
    }

    /**
     * Prints a message within a decorative border.
     *
     * @param message The message to print.
     */
    public void messageWrapper(String message) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
    }
}