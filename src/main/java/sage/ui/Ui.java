package sage.ui;

import sage.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface of the task management application
 */
public class Ui {
    /**
     * Display a welcome message to the users when the application starts
     */
    public void showWelcomeMessage() {
        System.out.println("______________________________________________________");
        System.out.println("Hello! I'm Sage");
        System.out.println("How can I help you today? ;)");
        System.out.println("______________________________________________________");
    }

    /**
     * Display a goodbye message to the users when they exit the application
     */
    public void showGoodbyeMessage() {
        System.out.println("______________________________________________________");
        System.out.println(" " + "Bye!! Hope you come and visit again soon!");
        System.out.println("______________________________________________________");
    }

    /**
     * Displays a general message to the user
     *
     * @param message The message to be displayed
     */
    public void showMessage(String message) {
        System.out.println("______________________________________________________");
        System.out.println(message);
        System.out.println("______________________________________________________");
    }

    /**
     * Display the list of tasks to the user
     *
     * @param tasks The TaskList object containing the tasks to be displayed
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("______________________________________________________");
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i <tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("______________________________________________________");
    }

<<<<<<< HEAD
    public void showLine() {
        System.out.println("______________________________________________________");
    }

=======
    /**
     * Reads a command input from the user
     *
     * @param scanner The scanner object used to read input from the console
     * @return The user input as a trimmed string
     */
>>>>>>> branch-A-JavaDoc
    public String readCommand(Scanner scanner) {
        return scanner.nextLine().trim();
    }
}
