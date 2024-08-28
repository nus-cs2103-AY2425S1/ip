package hue.UI;

import java.util.Scanner;
import hue.task.TaskList;


/**
 * Handles interactions with the user through the command line interface.
 */
public class UI {
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Displays a welcoming message to the user
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Hue");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    /**
     * Reads a command input from the user.
     *
     * @return The command input by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }
    /**
     * Displays a message indicating that the application is exiting.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The task list to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

}
