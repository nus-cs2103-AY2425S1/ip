package totoro.exception;

import totoro.command.CommandType;

/**
 * Represents an exception that is thrown when a required argument is missing
 * for a specific command in the Totoro chatbot
 * <p>
 *     This exception customises error messages based on the command type, guiding
 *     users to provide the necessary commands for successful command execution
 * </p>
 */
public class TotoroMissingArgException extends TotoroException {
    private CommandType command;

    public TotoroMissingArgException(CommandType command) {
        this.command = command;
    }

    @Override
    public String toString() {
        String message = "%s Missing argument. Please follow: %s";
        switch (command) {
            case MARK:
                return String.format(message, super.toString(), "mark <task number>");
            case UNMARK:
                return String.format(message, super.toString(), "unmark <task number>");
            case DELETE:
                return String.format(message, super.toString(), "delete <task number>");
            case FIND:
                return String.format(message, super.toString(), "find <keyword>");
            case TODO:
                return String.format(message, super.toString(), "todo <description>");
            case DEADLINE:
                return String.format(message, super.toString(), "deadline <description> /by <dd/MM/yy HH:mm>");
            case EVENT:
                return String.format(message, super.toString(), "event <description> /from <dd/MM/yy HH:mm> /to <dd/MM/yy HH:mm>");
            default:
                return super.toString() + "Missing argument";
        }
    }
}
