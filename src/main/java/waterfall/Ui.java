package waterfall;

import waterfall.task.Task;
import waterfall.task.TaskList;

import java.util.Scanner;

/**
 * Handles interactions with the user through command line. The <code>Ui</code> class
 * provides methods to display messages to the user and read inputs from the user.
 *
 * @author Wai Hong
 */

public class Ui {
    private final String chatBotName = "Waterfall";
    private final int indentSpace = 4;

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = ("Hualalalalala I'm " + chatBotName + "\n" + "What can I do for you?\n")
                .indent(indentSpace + 1);
        System.out.println(welcomeMessage);
    }

    /**
     * Displays a exit message to the user.
     */
    public void showByeMessage() {
        String byeMessage = ("Shhhhhhhhhhhh. Hope to see you again soon!\n").indent(indentSpace);
        System.out.println(byeMessage);
    }

    /**
     * Displays an error message to alert users on database loading error.
     */
    public void showLoadingError() {
        System.out.println("Oops! Something went wrong in loading the database!".indent(indentSpace));
    }

    /**
     * Displays an extra line.
     */
    public void showLine() {
        System.out.println(" ".repeat(indentSpace) + "____________________________________________________________\n");
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
    public void showError(String message) {
        System.out.println(" ".repeat(indentSpace + 1) + "Oops Water falls: " + message);
    }

    /**
     * Displays a message confirming that a task has been successfully added.
     *
     * @param task The task that has been added.
     */
    public void showAddMessage(Task task) {
        System.out.println(" ".repeat(indentSpace + 1) + "Successfully added a task to the waterfallll:");
        System.out.println(" ".repeat(indentSpace + 1) + task.toString());
    }

    /**
     * Displays a message confirming that a task has been successfully marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkMessage(Task task) {
        System.out.println(" ".repeat(indentSpace + 1) + "Huluhuluhulu, I've marked this task as done: ");
        System.out.println(" ".repeat(indentSpace + 1) + task.toString());
    }

    /**
     * Displays a message confirming that a task has been successfully marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkMessage(Task task) {
        System.out.println(" ".repeat(indentSpace + 1) + "Hohohohohoho, I've marked this task as not done: ");
        System.out.println(" ".repeat(indentSpace + 1) + task.toString());
    }

    /**
     * Displays a message confirming that a task has been successfully deleted.
     *
     * @param task The task that has been deleted.
     */
    public void showDeleteMessage(Task task) {
        System.out.println(" ".repeat(indentSpace + 1)
                + "Hehehehehehe, I've removed this task from the waterfall: ");
        System.out.println(" ".repeat(indentSpace + 1) + task.toString());
    }

    /**
     * Displays the list of tasks currently stored.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showTaskListMessage(TaskList taskList) {
        System.out.println(" ".repeat(indentSpace) + "Here's the tasks in your waterfall hualalala");
        taskList.printDetail(indentSpace + 1);
    }

    /**
     * Displays the list of tasks matching the string.
     *
     * @param taskList The list of filtered tasks to be displayed.
     */
    public void showSearchedTaskListMessage(TaskList taskList) {
        System.out.println(" ".repeat(indentSpace) + "Here's the matching tasks in your waterfall hualalala");
        taskList.printDetail(indentSpace + 1);
    }
}
