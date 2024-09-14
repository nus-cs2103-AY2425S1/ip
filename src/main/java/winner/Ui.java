package winner;

/**
 * Handles all interactions with the user.
 */
public class Ui {

    /**
     * Creates a Ui Object.
     */
    public Ui() {

    }

    /**
     * Displays a message in an indented format and with separators.
     *
     * @param msg Message to be displayed for the user.
     */
    public static void applyTemplate(String msg) {
        System.out.println(("-".repeat(100) + "\n"
                + msg + "\n"
                + "-".repeat(100)).indent(10));
    }

    /**
     * Returns a greeting message.
     *
     * @return String containing the "hi!" greeting.
     */
    public static String hiAgain() {
        return "hi!";
    }

    /**
     * Displays initial greeting message when the application starts.
     * It also provides some help on the commands the user can use.
     */
    public static void winnerSaysHi() {
        System.out.println(("-".repeat(100) + "\n"
                + "Hello! I am Winner, your personal task trackBOT!" + "\n"
                + "You can send me these commands in the form shown below so I can help you keep track of your tasks :"
                + "\n"
                + " ".repeat(5) + "- todo (task) --> tasks without any date/time attached" + "\n"
                + " ".repeat(5) + "- deadline (task) by (date) at (time) --> tasks with a deadline" + "\n"
                + " ".repeat(5) + "- event (task) from (start) to (end) --> tasks with a start and end date/time"
                + "\n"
                + "\n"
                + "You can also use these additional commands:" + "\n"
                + " ".repeat(5) + "- list --> shows you your list of tasks" + "\n"
                + " ".repeat(5) + "- mark (task number) --> mark the task number that you input as done" + "\n"
                + " ".repeat(5) + "- unmark (task number) --> mark the task number that you input as undone"
                + "\n"
                + " ".repeat(5) + "- delete (task number) --> remove the task number that you input from your "
                + "list of tasks" + "\n"
                + "-".repeat(100)).indent(10));
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public static String winnerSaysBye() {
        return """
                Hope I have been of service!
                Thank you and see you again soon :D
                Remember!!! A WINNER NEVER LOSES!!!""";
    }

}
