package waterfall;

import java.util.Scanner;

import waterfall.task.Task;
import waterfall.task.TaskList;

/**
 * Handles interactions with the user through command line. The <code>Ui</code> class
 * provides methods to display messages to the user and read inputs from the user.
 *
 * @author Wai Hong
 */

public class Ui {
    private final String chatBotName = "Waterfall";
    private final int indentSpace = 2;

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcomeMessage() {
        String welcomeMessage = ("Hualalalalala I'm " + chatBotName + "\n" + "What can I do for you?\n")
                .indent(indentSpace + 1);
        return welcomeMessage;
    }

    /**
     * Displays a exit message to the user.
     */
    public String showByeMessage() {
        String byeMessage = ("Shhhhhhhhhhhh. Hope to see you again soon!\n").indent(indentSpace);
        return byeMessage;
    }

    /**
     * Displays an error message to alert users on database loading error.
     */
    public String showLoadingError() {
        return "Oops! Something went wrong in loading the database!".indent(indentSpace);
    }

    /**
     * Displays an extra line.
     */
    public String showLine() {
        return " ".repeat(indentSpace) + "____________________________________________________________\n";
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    /**
     * Display an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return " ".repeat(indentSpace + 1) + "Oops Water falls: " + message;
    }

    /**
     * Displays a message confirming that a task has been successfully added.
     *
     * @param task The task that has been added.
     */
    public String showAddMessage(Task task) {
        String res = " ".repeat(indentSpace + 1) + "Successfully added a task to the waterfallll:\n";
        return res + " ".repeat(indentSpace + 1) + task.toString();
    }

    /**
     * Displays a message confirming that a task has been successfully marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showMarkMessage(Task task) {
        String res = " ".repeat(indentSpace + 1) + "Huluhuluhulu, I've marked this task as done: \n";
        return res + " ".repeat(indentSpace + 1) + task.toString();
    }

    /**
     * Displays a message confirming that a task has been successfully marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public String showUnmarkMessage(Task task) {
        String res = " ".repeat(indentSpace + 1) + "Hohohohoho, I've marked this task as not done: \n";
        return res + " ".repeat(indentSpace + 1) + task.toString();
    }

    /**
     * Displays a message confirming that a task has been successfully deleted.
     *
     * @param task The task that has been deleted.
     */
    public String showDeleteMessage(Task task) {
        String res = " ".repeat(indentSpace + 1)
                + "Hehehehehehe, I've removed this task from the waterfall: \n";
        return res + " ".repeat(indentSpace + 1) + task.toString();
    }

    /**
     * Displays the list of tasks currently stored.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public String showTaskListMessage(TaskList taskList) {
        String res = " ".repeat(indentSpace) + "Here's the tasks in your waterfall hualalala\n";
        return res + taskList.printDetail(indentSpace);
    }

    /**
     * Displays the list of tasks matching the string.
     *
     * @param taskList The list of filtered tasks to be displayed.
     */
    public String showSearchedTaskListMessage(TaskList taskList) {
        String res = " ".repeat(indentSpace) + "Here's the matching tasks in your waterfall hualalala\n";
        return res + taskList.printDetail(indentSpace);
    }
}
