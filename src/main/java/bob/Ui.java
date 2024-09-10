package bob;

/**
 * Represents the user interface for interacting with Bob chatbot.
 * The Ui class interacts with the user through the console.
 * It provides methods to read user input, display messages and show error messages.
 */
public class Ui {

    /**
     * Displays a welcome message to the user when the application starts.
     *
     * @return The welcome message as a string.
     */
    public String showWelcome() {
        return "Hello! I'm Bob the bot!\nHow can I help you?";
    }

    /**
     * Displays an error message to the user.
     *
     * @param e The exception containing the error message.
     * @return The error message as a string.
     */
    public String showError(Exception e) {
        assert e != null : "Exception cannot be null when showing error message.";
        return e.getMessage();
    }

    /**
     * Displays one or more messages to the user.
     * This method combines multiple messages into a single string for display.
     *
     * @param messages One or more strings to be combined and displayed.
     * @return The combined message as a string.
     */
    public String showMessage(String... messages) {
        StringBuilder combinedMessage = new StringBuilder();
        for (String message : messages) {
            combinedMessage.append(message);
        }
        return combinedMessage.toString();
    }

    /**
     * Displays a success message indicating that saved tasks were successfully loaded from storage.
     *
     * @return A success message as a string.
     */
    public String showLoadingSuccess() {
        return "Saved tasks are successfully loaded.";
    }

    /**
     * Displays a goodbye message when the user wants to exit the application.
     *
     * @return The goodbye message as a string.
     */
    public String showGoodbye() {
        return "Bye! Hope to see you again :)";
    }

    /**
     * Displays a message indicating that the user has no tasks in their task list.
     *
     * @return A message indicating an empty task list as a string.
     */
    public String showNoTasks() {
        return "There are 0 tasks in your list now. Start adding them!";
    }
}
