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
    public static String winnerSaysHi() {
        return """
                Hello! I am Winner, your personal task trackBOT!
                You can send me these commands in the form shown below so I can help you keep track of your tasks :
                     - todo (task) --> tasks without any date/time attached
                     - deadline (task) by (date) at (time) --> tasks with a deadline
                     - event (task) from (start) to (end) --> tasks with a start and end date/time
                     
                You can also use these additional commands:
                     - list --> shows you your list of tasks
                     - mark (task number) --> mark the task number that you input as done
                     - unmark (task number) --> mark the task number that you input as undone
                     - delete (task number) --> remove the task number that you input from your list of tasks
                """;
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
