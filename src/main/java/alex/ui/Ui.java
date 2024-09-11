package alex.ui;

/**
 * Represents the interface that interacts with the user.
 * An Ui object corresponds to an interface.
 */
public class Ui {
    private static final String byeMessage = "Bye. Hope to see you again soon!";

    /**
     * Displays the basic welcome message that greets
     * the user when the program is first run.
     *
     * @return string representing the welcome message
     */
    public String welcomeMessage() {
        return "Hello! I'm Alex 👋🏼🤖 \n" +
                "What can I do for you? ";
    }
    /**
     * Displays the basic goodbye message when the
     * program is closed and parts with the user.
     *
     * @return string representing the goodbye message
     */
    public String byeMessage() {
        return byeMessage;
    }
}
