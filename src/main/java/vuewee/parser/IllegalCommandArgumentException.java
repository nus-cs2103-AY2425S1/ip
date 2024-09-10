package vuewee.parser;

import vuewee.command.CommandType;
import vuewee.parser.description.DescriptionParser;

/**
 * This exception is thrown when an invalid argument is provided for a command.
 * It extends the {@link IllegalCommandException} class.
 */
public class IllegalCommandArgumentException extends IllegalCommandException {
    private CommandType command;
    private DescriptionParser<?> descriptionParser;
    private CommandOption<?>[] options;

    /**
     * Constructs a new IllegalCommandArgumentException.
     *
     * @param commandType       the command type
     * @param descriptionParser the description parser to use
     * @param options           the command options
     */
    public IllegalCommandArgumentException(CommandType commandType, DescriptionParser<?> descriptionParser,
            CommandOption<?>[] options) {
        super();
        this.command = commandType;
        this.descriptionParser = descriptionParser;
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
        if (this.descriptionParser != null) {
            usage.append(descriptionParser.getUsageFieldName());
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
