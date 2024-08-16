package sigma.exception;

import sigma.command.CommandType;

/**
 * Represents an exception thrown when a date format is invalid in the context of specific commands.
 * This exception is associated with commands such as `DEADLINE` and `EVENT` that require date input.
 */
public class SigmaInvalidDateException extends SigmaException {
    private CommandType command;

    /**
     * Constructs a {@code SigmaInvalidDateException} with the specified command type.
     *
     * @param command the command type that caused the exception
     */
    public SigmaInvalidDateException(CommandType command) {
        this.command = command;
    }

    /**
     * Returns a string representation of this exception, including a specific message
     * based on the command type that caused the exception.
     *
     * @return a string representation of this exception with usage instructions for the command
     */
    @Override
    public String toString() {
        String message = "%s Invalid Date format. Usage: %s";
        switch (command) {
        case DEADLINE:
            return String.format(message, super.toString(), "deadline <description> /by DD/MM/YY HH:MM");
        case EVENT:
            return String.format(message, super.toString(),
                    "event <description> /from DD/MM/YY HH:MM /to DD/MM/YY HH:MM");
        default:
            return super.toString() + " Invalid date.";
        }
    }
}
