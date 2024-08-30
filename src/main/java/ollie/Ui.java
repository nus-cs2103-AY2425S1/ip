package ollie;

import ollie.exception.OllieException;
import ollie.task.Task;

import java.util.Scanner;

/**
 * User Interface, responsible for interactions with the user.
 * E.g. Reading of user's input from the command line interface or displaying
 * the chatbot's responses to the user.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Read and return the user's input.
     *
     * @return String user's input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Display the greeting message.
     */
    public void showGreeting() {
        Ui.printResponse("Hello! I'm Ollie!\nWhat can I do for you?");
    }

    /**
     * Display the exit message.
     */
    public void showExit() {
        Ui.printResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Display list of task.
     *
     * @param tasks List of tasks.
     */
    public void showTaskList(TaskList tasks) {
        Ui.printResponse(tasks.toString());
    }

    /**
     * Display the task added and the current size of its list of tasks.
     */
    public void showAddTask(Task task, int size) {
        Ui.printResponse("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Display the task removed and the current size of its list of tasks.
     */

    public void showRemoveTask(Task task, int size) {
        Ui.printResponse("Noted. I've removed this task:\n  "
                + task.toString()
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Display the task that was marked as done.
     */
    public void showMarkAsDone(Task task) {
        Ui.printResponse("Nice! I've marked this task as done:\n  " + task.toString());
    }

    /**
     * Display the task that was marked as undone.
     */
    public void showMarkAsUndone(Task task) {
        Ui.printResponse("OK, I've marked this task as not done yet:\n  " + task.toString());
    }

    /**
     * Display the error message for any error during the loading of data from the file (database).
     */
    public void showLoadingError(OllieException e) {
        System.out.println("Loading Error:\n" + e.getMessage());
    }

    /**
     * Display the top and bottom dividers. Commonly used as a wrapper to wrap its message.
     */
    public static void printResponse(String s) {
        System.out.println("____________________________________________________________\n"
                + s
                + "\n____________________________________________________________");
    }
}
