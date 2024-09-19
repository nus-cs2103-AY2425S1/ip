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
    public void showWelcome() {
        System.out.println("______________________________");
        System.out.println("Hi! My name is bing.Bing");
        System.out.println("How can I help you?");
        System.out.println("______________________________");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showBye() {
        System.out.println("______________________________");
        System.out.println("Bye. Have a good day.");
        System.out.println("______________________________");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showByeMessage() {
        System.out.println("______________________________\n"
                + "Bye. Have a good day.\n"
                + "______________________________\n");
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList the list of tasks to display
     */
    public void showTasks(TaskList taskList) {
        System.out.println("______________________________");
        System.out.println("All tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println("______________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

}
