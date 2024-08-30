package alex.ui;

/**
 * Represents the interface that interacts with the user.
 * An Ui object corresponds to an interface.
 */
public class Ui {
    public static final String LINE = "----------------------------------------------------";
    public static final String byeMessage = "Bye. Hope to see you again soon!";

    /**
     * Displays the basic welcome message that greets
     * the user when the program is first run.
     */
    public void welcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Alex 👋🏼🤖 \n" +
                "What can I do for you? ");
        System.out.println(LINE);
    }
    /**
     * Displays the basic goodbye message when the
     * program is closed and parts with the user.
     */
    public void byeMessage() {
        System.out.println(LINE);
        System.out.println(byeMessage);
        System.out.println(LINE);
    }
}
