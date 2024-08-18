package sigma.exception;

import sigma.command.CommandType;

/**
 * The {@code SigmaInvalidDateException} class is a custom exception that extends
 * the {@code SigmaException} class. This exception is thrown when an invalid
 * date format is encountered for specific command types within the application.
 *
 * <p>This exception provides detailed error messages depending on the command type,
 * guiding the user on the correct usage of the command with the appropriate date
 * format.</p>
 *
 * <p>The supported command types are:</p>
 * <ul>
 *     <li>{@code DEADLINE}: Requires a date in the format "DD/MM/YY HH:MM" following the description.</li>
 *     <li>{@code EVENT}: Requires a start and end date in the format "DD/MM/YY HH:MM".</li>
 * </ul>
 *
 * <p>If the command type is not supported, a generic invalid date message is returned.</p>
 *
 * @see sigma.exception.SigmaException
 * @see sigma.command.CommandType
 */
public class SigmaInvalidDateException extends SigmaException {
    private CommandType command;

    /**
     * Constructs a new {@code SigmaInvalidDateException} with the specified command type.
     *
     * @param command the command type that triggered this exception
     */
    public SigmaInvalidDateException(CommandType command) {
        this.command = command;
    }

    /**
     * Returns a string representation of this exception, providing a detailed message
     * based on the command type.
     *
     * <p>If the command type is {@code DEADLINE} or {@code EVENT}, the message will
     * include the correct usage format for the date. For other command types, a generic
     * invalid date message is returned.</p>
     *
     * @return a string representation of this {@code SigmaInvalidDateException}
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
