package krona.ui;

import java.util.Scanner;
import krona.task.TaskList;

/**
 * The Ui class handles interactions with the user.
 * It is responsible for displaying messages, receiving user input,
 * and showing the list of tasks.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return The command entered by the user as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void welcome() {
        System.out.println("Hello! I'm Krona");
        System.out.println("What can I do for you?");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Error loading data from file.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the list of tasks to the user.
     * If the task list is empty, a message is shown indicating this.
     * Otherwise, the tasks in the list are displayed.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your list is currently empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
