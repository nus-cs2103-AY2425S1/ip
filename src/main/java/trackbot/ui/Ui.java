package trackbot.ui;

/**
 * Ui Handles user interface interactions and
 * has methods to display messages to the user.
 */
public class Ui {
    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "Hello from TrackBot!\n" + "How may I assist you?";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon! The application will automatically close in 5 seconds.";
    }

    /**
     * Displays a message to indicate a file not found error.
     */
    public void showFileNotFoundError() {
        System.out.println("File not found.");
    }

    /**
     * Returns an error message in string format.
     *
     * @param message The error message to display.
     * @return The formatted error message.
     */
    public String getErrorMessage(String message) {
        return "Error: " + message;
    }

}
