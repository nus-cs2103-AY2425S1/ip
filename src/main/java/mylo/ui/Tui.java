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
     * This method is invoked when the application first runs to greet the user with a custom message.
     *
     * @param welcomeMessage The welcome message to be displayed to the user.
     */
    @Override
    public void showWelcomeMessage(String welcomeMessage) {
        System.out.println(welcomeMessage);
    }

    /**
     * Updates the UI with Mylo's response message.
     * This method prints a message wrapped between two separator lines for better clarity.
     *
     * @param message The response message from Mylo to be displayed.
     */
    public void updateResponse(String message) {
        separator();
        System.out.println(message);
        separator();
    }

    /**
     * Updates the UI with the user input.
     * This method prints the user's input message as received from the GUI.
     *
     * @param message The user input message to be displayed.
     */
    public void updateInput(String message) {
        System.out.println(message);
    }
}
