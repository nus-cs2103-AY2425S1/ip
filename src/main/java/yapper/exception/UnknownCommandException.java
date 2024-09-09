package yapper.exception;

/**
 * Represents an exception that is thrown when the user enters an unknown or unrecognized command.
 * This exception provides an informative message to the user, including the unrecognized command
 * and a list of valid commands.
 */
public class UnknownCommandException extends YapperException {

    /**
     * Constructs an UnknownCommandException with the unrecognized command and a list of valid commands.
     *
     * @param command The unrecognized command entered by the user.
     */
    public UnknownCommandException(String command) {
        super("Oops! I don't know what \"" + command + "\" means, Boss. Here are some commands you can use:\n"
            + "1. list - Show all tasks\n"
            + "2. mark <task number> - Mark a task as done\n"
            + "3. unmark <task number> - Mark a task as not done\n"
            + "4. todo <description> - Add a new todo task\n"
            + "5. deadline <description> /by <date/time> - Add a new deadline task\n"
            + "6. event <description> /from <start time> /to <end time> - Add a new event task\n"
            + "7. delete <task number> - Delete a task\n"
            + "8. find <keyword> - Search for tasks containing the keyword\n"
            + "9. bye - Exit the application\n"
            + "\nPlease try again with one of the commands above.");
    }
}
