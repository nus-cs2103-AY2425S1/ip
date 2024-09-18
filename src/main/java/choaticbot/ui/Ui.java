package choaticbot.ui;

/**
 * Provides methods for printing formatted text to the console. This includes printing
 * welcome and goodbye messages, as well as general text and lines for visual separation.
 */
public class Ui {

    /**
     * Prints a given text surrounded by lines for visual separation.
     * The text is enclosed by lines of underscores to make it stand out.
     *
     * @param text The text to be printed.
     */
    public static void printText(String text) {
        String textToPrint = "____________________________________________________________\n"
                + text
                + "____________________________________________________________\n";

        System.out.println(textToPrint);
    }

    /**
     * Prints a line of underscores for visual separation in the console.
     * This can be used to create space or separate different sections of output.
     */
    public static void printLine() {
        String line = "____________________________________________________________\n";
        System.out.println(line);
    }

    /**
     * Returns a welcome message to greet the user.
     * The message is formatted to introduce ChoaticBot and prompt the user for input.
     *
     * @return A {@code String} containing the welcome message.
     */
    public static String getWelcomeMsg() {
        return "Hello! I'm ChoaticBot\n" + "What can I do for you?\n";
    }

    /**
     * Returns a goodbye message to the user.
     *
     * @return A {@code String} containing the farewell message.
     */
    public static String getByeMsg() {
        return "Bye. Hope to see you again soon!\n";
    }
}
