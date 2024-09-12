package chatbot;

/**
 * Formats string to standardize chatbot
 * input / output format.
 * Streamlines user interaction.
 *
 * @author celeschai
 */
public class Ui {
    /**
     * Prints out "Bee" logo in ASCII art.
     * Optimized for CLI
     */
    public static final String LOGO_CLI = " ____\n"
            + "|  _ \\  ___   ___\n"
            + "| |_/  / _ \\ / _ \\\n"
            + "| |_\\ |  __/|  __/\n"
            + "|____/ \\___| \\___|\n";

    /**
     * Prints out "Bee" logo in emoji.
     */
    public static final String LOGO = "🐝";

    /**
     * Prints out user guide.
     */
    public static final String HELP = """
            There are three types of task, you can add them by typing:
                todo <task name>
                deadline <task name> /by <time>
                event <task name> /from <time> /by <time>
            You can view all your tasks and their respective indices by:
                list
            You edit your tasks by:
                mark <task index>
                unmark <task index>
                delete <task index>
                
            Format your time in:
                yyyy-MM-dd HHmm (24Hr)
                    or yyyy-MM-dd hh:mm am (12Hr).
                You can replace - with \\ as well;
            """;

    /**
     * Ensures correct indentation for any text.
     * Indents single and multiline strings.
     *
     * @param multilineString Single or multiline string
     * @return Formatted string for chatbot output
     */
    public static String multiLineIndent(String multilineString) {
        String[] lines = multilineString.split("\n");

        // Create a StringBuilder for efficient concatenation
        StringBuilder modifiedString = new StringBuilder();

        // Append 4 characters in front of each line
        String indent = "    ";
        for (String line : lines) {
            modifiedString.append(indent).append(line).append("\n");
        }

        return modifiedString.toString();
    }

    /**
     * Formats standard output to be sandwiched by two lines for text interface.
     *
     * @param content text to be included between 2 lines
     */
    public static void printBtnLines(String content) {
        String line = "______________________________________"
                + "_______________________________________\n";

        System.out.println(line + multiLineIndent(content) + line);
    }

    /**
     * Formats chatbot response after adding a task.
     * Informs user of total number of tasks in todolist.
     *
     * @param added      String representation of task added.
     * @param numOfTasks Total number of tasks added.
     * @return           Success message for added task.
     */
    public static String addTaskResponse(String added, int numOfTasks) {
        return String.format(
                "Got it. I've added this task:\n   %s\nNow you have %d tasks in the list.",
                added, numOfTasks);
    }

    /**
     * Formats chatbot response after deleting a task.
     * Informs user of total number of tasks in todolist.
     *
     * @param deleted    String representation of task deleted.
     * @param numOfTasks Total number of tasks added.
     * @return           Success message for deleted task.
     */
    public static String deleteTaskResponse(String deleted, int numOfTasks) {
        return String.format(
                "Noted. I've removed this task:\n   %s\nNow you have %d tasks in the list.",
                deleted, numOfTasks);
    }
}
