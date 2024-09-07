package yapper.app;

/**
 * Provides utility methods for interacting with the user via the command line.
 * Includes methods for displaying messages and errors.
 */
public class Ui {

    private static final String DIVIDER = "-------------------------------------------------";
    private static final String NAME = "Yapper";

    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Displays the introduction message and prompts the user.
     */
    public static String yapperIntroduction() {
        String[] temp = {
            "Hello! I'm " + NAME,
            "What can I do for you?"
        };
        return wrapText(temp);
    }

    /**
     * Prints a line divider to the command line.
     */
    public static String showLine() {
        return DIVIDER + "\n";
    }

    /**
     * Wraps and displays a single string message with line dividers.
     *
     * @param text the message to be displayed
     */
    public static String wrapText(String text) {
        assert text != null : "Text should not be null";
        return showLine() + text + "\n" + showLine();
    }

    /**
     * Wraps and displays an array of strings as messages with line dividers.
     *
     * @param texts an array of messages to be displayed
     */
    public static String wrapText(String[] texts) {
        assert texts != null : "Text should not be null";
        StringBuilder sb = new StringBuilder(showLine());
        for (String s : texts) {
            sb.append(s).append("\n");
        }
        return sb.append(showLine()).toString();
    }

    /**
     * Displays an error message wrapped with line dividers.
     *
     * @param errorMessage the error message to be displayed
     */
    public static String errorCaught(String errorMessage) {
        return wrapText(errorMessage);
    }

}
