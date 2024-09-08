package dipsy.exception;

/**
 * Represents an exception that is thrown when an unrecognized or unknown command is encountered.
 * This exception provides the user with a helpful usage guide for valid commands.
 */
public class UnknownCommandException extends Exception {

    /**
     * Returns a detailed error message indicating that the command is unrecognized.
     * The message includes usage instructions for valid commands.
     *
     * @return The error message with usage instructions for valid commands.
     */
    @Override
    public String getMessage() {
        return "Unrecognized command.\n\n" + getUsage();
    }

    /**
     * Provides a usage guide for valid commands that can be executed in the application.
     * This guide includes descriptions and examples of various commands.
     *
     * @return A string containing the usage guide for valid commands.
     */
    private static String getUsage() {
        return "Usage: <command> [options]\n"
                + "\nCommands:\n"
                + "  todo <description>                            Create a new todo item\n"
                + "  deadline <description> /by <date>             Create a task with a deadline (yyyy-MM-dd)\n"
                + "  event <description> /from <start> /to <end>   Create an event with start and end dates\n"
                + "  mark <index>                                  Mark the task at the given index as done\n"
                + "  unmark <index>                                Unmark the task at the given index\n"
                + "  list                                          List all tasks\n"
                + "  list <date>                                   List tasks on the specified date\n"
                + "  bye                                           Exit the program\n"
                + "\nExamples:\n"
                + "  todo Buy treats for Dipsy\n"
                + "  deadline Submit report /by 2024-05-12\n"
                + "  event Conference /from 2024-08-12 /to 2024-08-14\n"
                + "  mark 1\n"
                + "\nDate format: yyyy-MM-dd";
    }
}
