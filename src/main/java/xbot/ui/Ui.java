package xbot.ui;

import java.util.Scanner;

import xbot.TaskList;
import xbot.XBotException;

/**
 * The Ui class handles all user interactions in the XBot application.
 * It manages input and output operations such as displaying messages, reading user commands, and showing errors.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays the list of tasks to the user.
     * If the list is empty, it informs the user that there are no tasks.
     *
     * @param list The TaskList containing the tasks to be displayed.
     */
    public String showTaskList(TaskList list) {
        String output;
        if (list.size() == 0) {
            output = ("Yayy!! You have no task in your list");
        } else {
            output = ("Here are the tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                output = output + (index + ". " + list.get(i).toString() + "\n");
            }
        }
        return output;
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * If the list is empty, it prints a message indicating no matching tasks were found.
     * Otherwise, it prints a numbered list of the matching tasks.
     *
     * @param list the list of tasks to display
     */
    public static String showMatchingTaskList(TaskList list) {
        if (list.size() == 0) {
            return ("There is no task description containing this keyword :(");
        } else {
            String output = ("Here are the matching tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                output = output + (index + ". " + list.get(i).toString());
            }
            return output;
        }
    }


    /**
     * Displays a welcome message to the user when the application starts.
     */
    public String showWelcome() {
        return ("Hello! I'm XBot\n" + "What can I do for you?");
    }

    /**
     * Displays a goodbye message to the user when the application terminates.
     */
    public String showBye() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Read the next command for the user input.
     *
     * @return The command input by the user as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message to the user.
     * This method is called when an XBotException is thrown during the application's execution.
     *
     * @param e The XBotException containing the error message to be displayed.
     */
    public String mainErrorMessage(XBotException e) {
        return ("Oh No!! " + e.getMessage());
    }

    /**
     * Closes the scanner resource to prevent resource leaks.
     * This method should be called when the application is about to terminate.
     */
    public void close() {
        scanner.close();
    }
}
