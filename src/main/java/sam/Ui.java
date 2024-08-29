package sam;
/**
 * The Ui class handles the user interface of the application.
 * It is responsible for displaying messages and interacting with the user.
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "----------------------------------------";

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Sam\nWhat can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the specified message to the user.
     *
     * @param message the message to be displayed
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}