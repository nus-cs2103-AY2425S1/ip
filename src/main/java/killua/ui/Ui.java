package killua.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import killua.task.Task;
import killua.util.TaskList;

/**
 * Represents the user interface of the Killua task manager.
 * It handles the display of messages and reading of user input.
 */
public class Ui {

    private final Scanner in;

    /**
     * Constructs a Ui instance with default input and output streams.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a Ui instance with specified input and output streams.
     *
     * @param in The input stream to read user input from.
     * @param out The output stream to print messages to.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
    }

    /**
     * Displays a welcome message and a list of available commands.
     */
    public String welcomeUser() {
        return """
        Welcome to Killua Task Manager!
        Here are some commands you can use:
            bye - Exit the application
            list - List all tasks
            mark <task number> - Mark a task as done
            unmark <task number> - Mark a task as not done yet
            delete <task number> - Delete a task
            todo <description> - Add a new todo task
            deadline <description> /by <yyyy-mm-dd> OR
            deadline <description> /by <yyyy-mm-dd hh:mm> - Add a new deadline task
            event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd> OR
            event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm> - Add a new event task""";
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list after the addition.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have "
                + taskCount + " tasks in the list.";
//        printLine();
//        System.out.println("Got it. I've added this task:");
//        System.out.println("  " + task);
//        System.out.println("Now you have " + taskCount + " tasks in the list.");
//        printLine();
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     */
    public String showTaskDeleted(Task task) {
        return "OK, I've deleted this task:\n" + task;
    }

    /**
     * Displays a farewell message when the application is exiting.
     */
    public String showBye() {
        return "Bye!";
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks The task list to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        String message;
        if (tasks.isEmpty()) {
            message = "Your task list is empty.";
        } else {
            message = "Here are the tasks in your list:\n" + tasks.getTasksString();
        }
        return message;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done yet.
     *
     * @param task The task that was marked as not done yet.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Displays tasks that are scheduled for a specific date.
     *
     * @param tasks The task list containing the tasks to be displayed.
     * @param date The date for which tasks are to be displayed.
     */
    public String showTasksOnDate(TaskList tasks, LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks on ").append(date).append(":\n");

        boolean hasTasks = false;
        for (Task task : tasks.getTasksOnDate(date).getTasks()) {
            sb.append("  ").append(task).append("\n");
            hasTasks = true;
        }

        if (!hasTasks) {
            sb.append("No tasks found for this date.\n");
        }

        return sb.toString();
    }

    /**
     * Reads and returns a command from user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Displays the tasks that match a specified keyword.
     * Prints a message to indicate whether any matching tasks were found.
     * If matching tasks are found, prints each matching task to the console.
     * Otherwise, prints a message indicating that no matching tasks were found.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public String showMatchedTask(TaskList tasks) {
        String message;
        if (tasks.isEmpty()) {
            message = "No matching task in your list!";
        } else {
            message = "Here are the Matching tasks in your list:" + tasks.getTasksString();
        }
        return message;
    }
}
