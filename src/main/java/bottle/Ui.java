package bottle;

import java.util.ArrayList;
import java.util.Scanner;

import bottle.task.Task;
import bottle.task.TaskList;

/**
 * The Ui class handles user interactions, including displaying messages and
 * receiving user input.
 */
public class Ui {
    /**
     * The constant line break used for formatting output.
     */
    static final String LINE_BREAK = "\n____________________________________________________________\n";

    /**
     * The welcome message displayed to the user.
     */
    private final String welcomeMsg =
            " Hello! I'm bottle.Bottle\n"
                    + " What can I do for you?";

    /**
     * The goodbye message displayed when the user exits.
     */
    private final String byeMsg = " Bye. Hope to see you again soon!";

    /**
     * The Scanner for reading user input.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Formats a string with line breaks for display.
     *
     * @param str the string to format
     * @return the formatted string with line breaks
     */
    public String printWithBreak(String str) {
        return LINE_BREAK + str + LINE_BREAK;
    }

    /**
     * Prints the welcome message.
     *
     * @return the formatted welcome message
     */
    public String printWelcomeMsg() {
        return printWithBreak(welcomeMsg);
    }

    /**
     * Prints the goodbye message.
     *
     * @return the formatted goodbye message
     */
    public String printByeMsg() {
        return printWithBreak(byeMsg);
    }

    /**
     * Prints a message indicating a task has been marked as done.
     *
     * @param task the task that was marked
     * @return the formatted message
     */
    public String printMark(Task task) {
        return printWithBreak("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints a message indicating a task has been marked as not done.
     *
     * @param task the task that was unmarked
     * @return the formatted message
     */
    public String printUnMark(Task task) {
        return printWithBreak("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Prints a message indicating a task has been added to the list.
     *
     * @param taskList the current list of tasks
     * @return the formatted message
     */
    public String printTaskAddedMessage(ArrayList<Task> taskList) {
        return printWithBreak("Got it. I've added this task:\n   " + taskList.get(taskList.size() - 1)
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Reads user input from the console.
     *
     * @return the user's input as a string
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the current list of tasks.
     *
     * @param taskList the list of tasks to display
     * @return the formatted task list
     */
    public String printTaskList(TaskList taskList) {
        return printWithBreak(taskList.toString());
    }

    /**
     * Prints a message indicating a task has been deleted from the list.
     *
     * @param taskList the current list of tasks
     * @param task     the task that was deleted
     * @return the formatted message
     */
    public String printDeleteMsg(ArrayList<Task> taskList, Task task) {
        return printWithBreak("Got it. I've removed this task:\n   " + task
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
