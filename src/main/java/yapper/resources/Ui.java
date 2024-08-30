package yapper.resources;

/**
 * Provides utility methods for interacting with the user via the command line.
 * Includes methods for displaying messages and errors.
 */
public class Ui {

    private static final String DIVIDER = "-------------------------------------------------";
    private static final String NAME = "yapper.main.Yapper";

    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Displays the introduction message and prompts the user.
     */
    public void intro() {
        String[] temp = {
            "Hello! I'm " + NAME,
            "What can I do for you?"
        };
        wrapText(temp);
    }

    /**
     * Prints a line divider to the command line.
     */
    public static void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Wraps and displays a single string message with line dividers.
     *
     * @param text the message to be displayed
     */
    public static void wrapText(String text) {
        showLine();
        System.out.println(text);
        showLine();
    }

    /**
     * Wraps and displays an array of strings as messages with line dividers.
     *
     * @param texts an array of messages to be displayed
     */
    public static void wrapText(String[] texts) {
        System.out.println(DIVIDER);
        for (String s : texts) {
            System.out.println(s);
        }
        System.out.println(DIVIDER);
    }

    /**
     * Displays an error message wrapped with line dividers.
     *
     * @param errorMessage the error message to be displayed
     */
    public static void errorCaught(String errorMessage) {
        wrapText(errorMessage);
    }

}
