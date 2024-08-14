package sigma.exception;

import sigma.command.CommandType;

/**
 * Thrown when a required argument is missing for a command in the Sigma application.
 */
public class SigmaMissingArgException extends SigmaException {
    private CommandType command;

    /**
     * Constructs a SigmaMissingArgException with the specified command type.
     *
     * @param command the command type associated with the missing argument(s)
     */
    public SigmaMissingArgException(CommandType command) {
        this.command = command;
    }

    /**
     * Returns a detailed error message indicating the missing argument(s) and the correct usage of the command.
     *
     * @return a string representing the error message with usage instructions
     */
    @Override
    public String toString() {
        String message = "%s Missing argument(s). Usage: %s";
        switch (command) {
        case TODO:
            return String.format(message, super.toString(), "todo <description>");
        case DEADLINE:
            return String.format(message, super.toString(), "deadline <description> /by <date>");
        case EVENT:
            return String.format(message, super.toString(), "event <description> /from <start> /to <end>");
        case MARK:
            return String.format(message, super.toString(), "mark <task_number>");
        case UNMARK:
            return String.format(message, super.toString(), "unmark <task_number>");
        case DELETE:
            return String.format(message, super.toString(), "delete <task_number>");
        default:
            return super.toString() + " Invalid argument.";
        }
    }
}
