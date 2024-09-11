package mylo.ui;

/**
 * Represents the text-based user interface for the Mylo application.
 * <p></p>
 * <p>This class is responsible for displaying user input and Mylo's response.</p>
 *
 * @author cweijin
 */
public class Tui implements Ui {
    /**
     * Prints a separator line for better readability in the UI.
     */
    public void separator() {
        System.out.println("--------------------------------");
    }

    /**
     * Prints a welcome message to the user when the application starts.
     */
    @Override
    public void showWelcomeMessage(String welcomeMessage) {
        System.out.println(welcomeMessage);
    }

    /**
     * Updates the UI with Mylo's response message.
     * This method prints a message wrapped between separator lines for clarity.
     */
    public void updateResponse(String message) {
        separator();
        System.out.println(message);
        separator();
    }

    /**
     * Update the UI with user input.
     * This method prints the message input by user from the GUI.
     */
    public void updateInput(String message) {
        System.out.println(message);
    }
}
