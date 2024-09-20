package totoro.exception;

import totoro.command.CommandType;

/**
 * Represents an exception that is thrown when an invalid date is provided
 * for a specified command in the Totoro chatbot
 * <p>
 *     This exception customises error messages based on the command type,
 *     providing the correct date format for command that involve deadlines or events
 * </p>
 */
public class TotoroInvalidDateException extends TotoroException {
    private CommandType command;

    public TotoroInvalidDateException(CommandType command) {
        this.command = command;
    }

    @Override
    public String toString() {
        String message = "%s Invalid date. Please follow: %s";
        switch (command) {
            case DEADLINE:
                return String.format(message, super.toString(), "deadline <description> /by <dd/MM/yy HH:mm>");
            case EVENT:
                return String.format(message, super.toString(), "event <description> /from <dd/MM/yy HH:mm> /to <dd/MM/yy HH:mm>");
            default:
                return super.toString() + "Invalid date";
        }
    }
}
