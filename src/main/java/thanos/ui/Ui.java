package thanos.ui;

/**
 * Handles the user interface interactions for displaying messages and tasks.
 * <p>
 * The {@code Ui} class provides methods to output various types of messages and task information
 * to the console. It includes methods for displaying welcome messages, task lists, and notifications
 * about task additions, removals, and status changes.
 * </p>
 */
public class Ui {
    /**
     * Displays a string message to the console and adds a divider.
     *
     * @param s the string message to display.
     */
    public void display(String s) {
        System.out.print(s);
        printDivider();
    }

    /**
     * Prints a welcome message to the user.
     */
    public void displayWelcome() {
        this.display("Hello! I'm Thanos!\nWhat can I do for you?\n");
    }

    /**
     * Prints a horizontal divider line.
     */
    public static void printDivider() {
        System.out.println("-".repeat(50));
    }
}
