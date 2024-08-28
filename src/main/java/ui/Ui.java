package ui;

import java.util.Scanner;

import main.TaskList;
import tasks.Task;

/**
 * The {@code Ui} class handles interactions with the user, including
 * reading user input and displaying messages.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a {@code Ui} object and initializes the Scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return the user's input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showMessage("Hello! I am Pro Yapper!\nWhat can I do for you?");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        showMessage("Hope to see you again soon!");
    }

    /**
     * Displays a general message to the user.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.err.println(message);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList the {@code TaskList} containing tasks to be displayed
     */
    public void showTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            showMessage("No tasks in your list.");
            return;
        }
        showMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int lstNum = i + 1;
            Task next = taskList.get(i);
            showMessage(lstNum + ". " + next.toString());
        }
    }
}
