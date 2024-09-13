package bottleopener.ui;

/**
 * The {@code Ui} class provides utility methods for formatting messages
 * with a predefined spacer. It helps in wrapping messages to maintain
 * consistency in the output displayed by the application.
 */
public class Ui {
    private static final String SPACER = "-----------------------------------\n";

    /**
     * Wraps the given message with a spacer before and after it.
     * If the message does not end with a newline, one will be added before appending
     * the closing spacer.
     *
     * @param message The message to be wrapped.
     * @return A string with the message wrapped between two spacer lines.
     */
    public static String wrapSpacer(String message) {
        if (message.endsWith("\n")) {
            return SPACER + message + SPACER;
        } else {
            return SPACER + message + "\n" + SPACER;
        }
    }

    /**
     * Returns a goodbye message wrapped with spacers.
     *
     * @return The formatted goodbye message.
     */
    public static String showGoodbye() {
        return wrapSpacer("Bye! See you next time!\n");
    }

    /**
     * Returns a message indicating a task has been marked as done.
     *
     * @return The formatted mark-as-done message.
     */
    public static String showMark() {
        return wrapSpacer("Good job completing it!\n");
    }

    /**
     * Returns a message indicating a task has been marked as not done.
     *
     * @return The formatted mark-as-undone message.
     */
    public static String showUnmark() {
        return wrapSpacer("I have added it back to the list!\n");
    }

    /**
     * Returns a message indicating a task has been deleted.
     *
     * @return The formatted delete message.
     */
    public static String showDelete() {
        return wrapSpacer("I have removed the item!\n");
    }

    /**
     * Returns an error message indicating an inappropriate number was provided.
     *
     * @return The formatted error message.
     */
    public static String showAppropriateNumberError() {
        return wrapSpacer("Please provide an appropriate number!\n");
    }

    /**
     * Returns an error message indicating an incorrect command format.
     *
     * @return The formatted error message.
     */
    public static String showCommandFormatError() {
        return wrapSpacer("Please use the correct format!\n");
    }

    /**
     * Returns an error message indicating missing information.
     *
     * @return The formatted error message.
     */
    public static String showMissingInfoError() {
        return wrapSpacer("You have missing information! Please try again!\n");
    }

    /**
     * Displays a message indicating that the tasks matching the search criteria have been found.
     *
     * @return A string message stating that the relevant tasks have been found.
     */
    public static String showFoundTasks() {
        return "Here are all the tasks I could find!\n";
    }

    /**
     * Displays an error message indicating that the user has provided an incorrect date/time format.
     *
     * @return A string message requesting the user to use the correct date/time format.
     */
    public static String showInvalidDateFormatError() {
        return wrapSpacer("Please use the correct date/time format!\n");
    }

}
