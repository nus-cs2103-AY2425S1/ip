package edith;

/**
 * This Ui class handles all interactions with the user.
 * It is responsible for displaying messages, prompts, errors, and other user interface elements.
 */
public class Ui {
    private static final String LINE_BREAK = "\n_______________________________________________________________________\n";
    private static final String INDENTATION = "    ";

    /**
     * Displays a greeting message when the chatbot starts.
     */
    public void showGreeting() {
        String greeting = "Hello! I am EDITH, your personal chatbot companion:)" + "\nHow may I assist you?";
        System.out.println(LINE_BREAK + greeting + LINE_BREAK);
    }

    /**
     * Displays an exit message when the chatbot ends.
     */
    public void showExitMessage() {
        String exitMessage = "It has been my pleasure helping you. Hope to see you again soon!";
        System.out.println(INDENTATION + exitMessage + LINE_BREAK);
    }

    /**
     * Displays a prompt to indicate that the user can input the next instruction.
     */
    public void showPrompt() {
        System.out.println("Your next instruction: ");
    }

    /**
     * Displays a line break to separate sections of output for better readability.
     */
    public void showLineBreak() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Displays a message with indentation for better formatting.
     *
     * @param message The message to display with indentation.
     */
    public void showIndentedMessage(String message) {
        System.out.println(INDENTATION + message);
    }

    /**
     * Displays an error message with indentation and a line break for better visibility.
     *
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println(INDENTATION + message + LINE_BREAK);
    }

    /**
     * Returns an error message for invalid date/time formats.
     *
     * @return A string message indicating the correct date/time format.
     */
    public String invalidDateTimeError() {
        return "Invalid date/time format. Please use 'day/month/year HHmm' (e.g '13/9/2024 1800').";
    }
}
