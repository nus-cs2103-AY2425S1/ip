package papadom;
/**
 * Handles user interaction by displaying messages.
 */
public class Ui {
    private String openingLine = "____________________________________________________________\n";
    private String closingLine = "\n____________________________________________________________";
    /**
     * Outputs a formatted message to the user.
     *
     * @param message The message to be displayed.
     */
    public void output(String message) {
        System.out.println(openingLine + message + closingLine);
    }
    /**
     * Displays a welcome message when the chatbot starts.
     */
    public void welcomeMessage() {
        System.out.println(openingLine + " Hello! I'm Papadom\n What can I do for you?" + closingLine);
    }
    /**
     * Displays a farewell message when the chatbot exits.
     */
    public void exitMessage() {
        System.out.println(openingLine + " Bye. Hope to see you again soon!" + closingLine);
    }
}

