package papadom;
/**
 * Handles user interaction by displaying messages.
 */
public class Ui {
    private final String OPENING_LINE = "____________________________________________________________\n";
    private final String CLOSING_LINE = "\n____________________________________________________________";
    /**
     * Outputs a formatted message to the user.
     *
     * @param message The message to be displayed.
     */
    public void output(String message) {
        System.out.println(OPENING_LINE + message + CLOSING_LINE);
    }
    /**
     * Displays a welcome message when the chatbot starts.
     */
    public void welcomeMessage() {
        System.out.println(OPENING_LINE + " Hello! I'm Papadom\n What can I do for you?" + CLOSING_LINE);
    }
    /**
     * Displays a farewell message when the chatbot exits.
     */
    public void exitMessage() {
        System.out.println(OPENING_LINE + " Bye. Hope to see you again soon!" + CLOSING_LINE);
    }
}

