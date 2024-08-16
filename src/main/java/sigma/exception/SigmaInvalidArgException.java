package sigma.exception;

import sigma.command.CommandType;

/**
 * Thrown when invalid arguments are provided to a command in the Sigma application.
 */
public class SigmaInvalidArgException extends SigmaException {
    private CommandType command;

    /**
     * Constructs a SigmaInvalidArgException with the specified command type.
     *
     * @param command the command type associated with the invalid arguments
     */
    public SigmaInvalidArgException(CommandType command) {
        this.command = command;
    }

    /**
     * Returns a detailed error message indicating the correct usage of the command.
     *
     * @return a string representing the error message with usage instructions
     */
    @Override
    public String toString() {
        String message = "%s Invalid argument(s). Usage: %s";
        switch (command) {
        case TODO:
            return String.format(message, super.toString(), "todo <description>");
        case DEADLINE:
            return String.format(message, super.toString(), "deadline <description> /by DD/MM/YY HH:MM");
        case EVENT:
            return String.format(message,
                    super.toString(), "event <description> /from DD/MM/YY HH:MM /to DD/MM/YY HH:MM");
        case MARK:
            return String.format(message, super.toString(), "mark <task_number>");
        case UNMARK:
            return String.format(message, super.toString(), "unmark <task_number>");
        case DELETE:
            return String.format(message, super.toString(), "delete <task_number>");
        case FIND:
            return String.format(message, super.toString(), "find <keyword>");
        default:
            return super.toString() + " Invalid argument(s).";
        }
    }
}
