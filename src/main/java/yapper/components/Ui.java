package yapper.components;

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
    public String yapperIntroduction() {
        return display(String.format("Hello, I'm %s", NAME),
                "Type 'help' or '?' for a list of available commands");
    }

    /**
     * Prints a line divider to the command line.
     */
    public static String showLine() {
        return DIVIDER + "\n";
    }

    /**
     * Wraps and displays an array of strings as messages with line dividers.
     *
     * @param texts an array of messages to be displayed
     */
    public String display(String... texts) {
        assert texts != null : "Text should not be null";
        StringBuilder response = new StringBuilder();
        for (String text : texts) {
            response.append(text).append("\n");
        }
        return response.toString();
    }

}
