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
     * Returns initial greeting message when the application starts.
     * It also provides some help on the commands the user can use.
     *
     * @return String containing initial greeting message.
     */
    public static String winnerSaysHi() {
        return """
                Hello! I am Winner, your personal task trackBOT!
                Type "help" if you need help with commands!
                """;
    }

    /**
     * Returns a help page providing commands to users.
     *
     * @return String containing help page.
     */
    public static String winnerGivesHelp() {
        return """
                Use these commands for me to add tasks:
                     - todo (task) --> tasks without any date/time attached
                     - deadline (task) by (date) at (time) --> tasks with a deadline
                     - event (task) from (start) to (end) --> tasks with a start and end date/time
                You can also use these additional commands:
                     - list --> shows you your list of tasks
                     - mark (task number) --> mark the task number that you input as done
                     - unmark (task number) --> mark the task number that you input as undone
                     - delete (task number) --> remove the task number that you input from your list of tasks
                     - find (keyword) --> shows you all your tasks with the keyword
                """;
    }

    /**
     * Displays a farewell message when the user exits the application.
     *
     * @return String containing farewell message.
     */
    public static String winnerSaysBye() {
        return """
                Hope I have been of service!
                Thank you and see you again soon :D
                Remember!!! A WINNER NEVER LOSES!!!""";
    }

}
