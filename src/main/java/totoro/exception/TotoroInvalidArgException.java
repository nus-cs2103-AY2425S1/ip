package totoro.exception;

import totoro.command.CommandType;

/**
 * Represents an exception that is thrown when an invalid argument is provided
 * for a specified command in the Totoro chatbot
 * <p>
 *     This exception customised error messages based on the command type,
 *     helping users by providing the correct usage format or the command they attempted
 * </p>
 */
public class TotoroInvalidArgException extends TotoroException {
    private CommandType command;

    public TotoroInvalidArgException(CommandType command) {
        this.command = command;
    }

    @Override
    public String toString() {
        String message = "%s Invalid argument. Please use: %s";
        switch (command) {
            case MARK:
                return String.format(message, super.toString(), "mark <task number>");
            case UNMARK:
                return String.format(message, super.toString(), "unmark <task number");
            case DELETE:
                return String.format(message, super.toString(), "delete <task number");
            case FIND:
                return String.format(message, super.toString(), "find <keyword>");
            case TODO:
                return String.format(message, super.toString(), "todo <description>");
            case DEADLINE:
                return String.format(message, super.toString(), "deadline <description> /by <dd/MM/yy HH:mm>");
            case EVENT:
                return String.format(message, super.toString(), "event <description> /from <dd/MM/yy HH:mm> /to <dd/MM/yy HH:mm>");
            default:
                return super.toString() + "Invalid argument";
        }
    }
}
