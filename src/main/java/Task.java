import java.util.ArrayList;

/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static int taskCount = 0;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done or not done.
     *
     * @param status The status to set (true for done, false for not done).
     */
    public void markAsDone(boolean status) {
        this.isDone = status;
    }

    /**
     * Marks a specific task as done based on the task number.
     *
     * @param taskNumber The task number to mark as done.
     * @throws OllieException If the task number is invalid.
     */
    public static void markTaskAsDone(int taskNumber) throws OllieException {
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
    public static void unmarkTaskAsDone(int taskNumber) throws OllieException {
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
    public static void listTasks() {
        String border = "--".repeat(30);
        System.out.println(border);
        System.out.println("Here are the tasks in your list ☺ :");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(border);
    }

    /**
     * Validates the task's description based on the command.
     *
     * @param command The command string used to create the task.
     * @throws OllieException If the description is invalid.
     */
    public abstract void validateDescription(String command) throws OllieException;

    /**
     * Adds a new task to the list and prints the formatted output.
     *
     * @param task The task to add.
     */
    public static void addTask(Task task) {
        tasks.add(task);
        taskCount = tasks.size();
        printTaskAdded(task);
    }

    /**
     * Prints the formatted message when a task is added.
     *
     * @param task The task that was added.
     */
    private static void printTaskAdded(Task task) {
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
    public static void deleteTask(int taskNumber) throws OllieException {
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
    public static void messageWrapper(String message) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The task's status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}