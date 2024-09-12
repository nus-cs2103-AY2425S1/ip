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
     * Prints a welcome message to greet the user.
     * The message is formatted to introduce ChoaticBot and prompt the user for input.
     */
    public static void printWelcomeMsg() {
        String welcomeMsg = "Hello! I'm ChoaticBot\n" + "What can I do for you?\n";
        printText(welcomeMsg);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static void printByeMsg() {
        String byeMsg = "Bye. Hope to see you again soon!\n";
        printText(byeMsg);
    }
}
