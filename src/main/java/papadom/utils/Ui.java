package papadom.utils;
/**
 * Handles user interaction by displaying messages.
 */
public class Ui {
    private static final String OPENING_LINE = "____________________________________________________________\n";
    private static final String CLOSING_LINE = "\n____________________________________________________________";
    /**
     * Outputs a formatted message to the user.
     *
     * @param message The message to be displayed.
     */
    public String output(String message) {
        assert !message.isEmpty() : "Message cannot be empty";
        return OPENING_LINE + message + CLOSING_LINE;
    }
    /**
     * Displays a welcome message when the chatbot starts.
     */
    public static String welcomeMessage() {
       return OPENING_LINE + " Hello! I'm Papadom\n What can I do for you?" + CLOSING_LINE;
    }
    /**
     * Displays a farewell message when the chatbot exits.
     */
    public String exitMessage() {
        return OPENING_LINE + " Bye. Hope to see you again soon!" + CLOSING_LINE;
    }
}

