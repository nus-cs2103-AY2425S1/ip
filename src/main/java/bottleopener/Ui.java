package bottleopener;

/**
 * The {@code BottleOpener.Ui} class handles the user interface for the BottleOpener.BottleOpener chatbot.
 * It provides methods for displaying messages to the user with consistent formatting.
 */
public class Ui {
    private static final String SPACER = "-----------------------------------\n";
    private String botName;

    /**
     * Constructs a new {@code BottleOpener.Ui} object with the specified bot name.
     *
     * @param botName The name of the chatbot.
     */
    public Ui(String botName) {
        this.botName = botName;
    }

    /**
     * Wraps a given message with a spacer line above and below it.
     * If the message does not end with a newline, it adds one before the closing spacer.
     *
     * @param message The message to be wrapped with spacers.
     * @return The formatted message with spacers.
     */
    public String wrapSpacer(String message) {
        if (message.endsWith("\n")) {
            return SPACER + message + SPACER;
        } else {
            return SPACER + message + "\n" + SPACER;
        }
    }

    /**
     * Returns a greeting message wrapped with spacers.
     *
     * @return The formatted greeting message.
     */
    public String showGreeting() {
        String message = "Hello! I'm " + this.botName + ".\n" + "What can I do for you?\n";
        return wrapSpacer(message);
    }

    /**
     * Returns a goodbye message wrapped with spacers.
     *
     * @return The formatted goodbye message.
     */
    public String showGoodbye() {
        return wrapSpacer("Bye! See you next time!\n");
    }

    /**
     * Returns a message indicating a task has been marked as done.
     *
     * @return The formatted mark-as-done message.
     */
    public String showMark() {
        return wrapSpacer("Good job completing it!\n");
    }

    /**
     * Returns a message indicating a task has been marked as not done.
     *
     * @return The formatted mark-as-undone message.
     */
    public String showUnmark() {
        return wrapSpacer("I have added it back to the list!\n");
    }

    /**
     * Returns a message indicating a task has been deleted.
     *
     * @return The formatted delete message.
     */
    public String showDelete() {
        return wrapSpacer("I have removed the item!\n");
    }

    /**
     * Returns an error message indicating an inappropriate number was provided.
     *
     * @return The formatted error message.
     */
    public String showAppropriateNumberError() {
        return wrapSpacer("Please provide an appropriate number!\n");
    }

    /**
     * Returns an error message indicating an incorrect command format.
     *
     * @return The formatted error message.
     */
    public String showCommandFormatError() {
        return wrapSpacer("Please use the correct format!\n");
    }

    /**
     * Returns an error message indicating missing information.
     *
     * @return The formatted error message.
     */
    public String showMissingInfoError() {
        return wrapSpacer("You have missing information! Please try again!\n");
    }

    public String showFoundTasks() {
        return "Here are all the tasks I could find!\n";
    }

    public String showInvalidDateFormatError() {
        return wrapSpacer("Please use the correct date/time format!\n");
    }

}
