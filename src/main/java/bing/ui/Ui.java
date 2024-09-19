package bing.ui;

import java.util.Scanner;
import bing.task.TaskList;


/**
 * Handles interactions with the user.
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads and returns the next command from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "______________________________" + "\n" +
                "Hi! My name is bing.Bing" + "\n" +
                "How can I help you?" + "\n" +
                "______________________________";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showBye() {
        return "______________________________" + "\n" +
                "Bye. Have a good day." + "\n" +
                "______________________________";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showByeMessage() {
        return "______________________________\n"
                + "Bye. Have a good day.\n"
                + "______________________________\n";
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList the list of tasks to display
     */
    public String showTasks(TaskList taskList) {
        String ans = "";
        ans = ans + "______________________________\n";
        ans = ans + "All tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            ans = ans + (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        ans = ans + "______________________________";
        return ans;
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public String showError(String message) {
        return "Error: " + message;
    }

}
