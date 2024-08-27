package vuewee.parser;

import vuewee.command.CommandType;

/**
 * This exception is thrown when an invalid argument is provided for a command.
 * It extends the {@link IllegalCommandException} class.
 */
public class IllegalCommandArgumentException extends IllegalCommandException {
    private CommandType command;
    private boolean hasDescription;
    private boolean isIntegerDescription;
    private CommandOption<?>[] options;

    /**
     * Constructs a new IllegalCommandArgumentException.
     *
     * @param command              the command type
     * @param hasDescription       if the command has a description
     * @param isIntegerDescription if the description is an integer
     * @param options              the command options
     */
    public IllegalCommandArgumentException(CommandType command, boolean hasDescription, boolean isIntegerDescription,
            CommandOption<?>[] options) {
        super();
        this.command = command;
        this.hasDescription = hasDescription;
        this.isIntegerDescription = isIntegerDescription;
        this.options = options;
    }

    /**
     * Returns the error message for this exception which displays as a usage
     * message.
     *
     * @return the error message
     */
    @Override
    public String getMessage() {
        // Create a usage string for the command
        StringBuilder usage = new StringBuilder(CommandType.toString(command));
        if (hasDescription) {
            usage.append(isIntegerDescription ? " <value>" : " <description>");
        }
        for (CommandOption<?> option : options) {
            usage.append(" /");
            usage.append(option.getOption());
            usage.append(" <");
            usage.append(option.getDescription());
            usage.append(">");
        }

        return "Invalid " + CommandType.toString(command) + " format. Usage: " + usage.toString();
    }
}
