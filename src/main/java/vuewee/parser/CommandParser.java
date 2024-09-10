package vuewee.parser;

import vuewee.command.CommandType;
import vuewee.parser.description.DescriptionParser;

/**
 * The CommandParser class is responsible for parsing and extracting information
 * from a command input string. It supports parsing commands with or without a
 * description, and with or without an integer parameter.
 */
public class CommandParser {
    private String argument; // Input text after command title

    private CommandType commandType;

    /**
     * Creates a new CommandParser object with the given input string. Parses the
     * input string to extract the command type and arguments/parameters.
     *
     * @param input The input string to parse.
     * @throws IllegalCommandException
     */
    public CommandParser(String input) throws IllegalCommandException {
        String[] parts = input.split(" ", 2);
        this.commandType = CommandType.fromString(parts[0]);
        this.argument = parts.length > 1 ? parts[1] : "";
    }

    /**
     * Parses the input string to extract the String description.
     *
     * @param descriptionParser The description parser to use.
     *
     * @return The parsed description of type T.
     */
    public <T> T parse(DescriptionParser<T> descriptionParser) {
        return this.parse(descriptionParser, new CommandOption[0]);
    }

    /**
     * Parses the input string and extracts the description. Uses expectedOptions to
     * parse the options in the input string.
     *
     * @param descriptionParser    The description parser to use.
     * @param isIntegerDescription Whether the description is an integer.
     * @param expectedOptions      The expected options to parse as a CommandOption
     *                             type.
     *
     * @return The parsed description of type T.
     */
    public <T> T parse(DescriptionParser<T> descriptionParser, CommandOption<?>... expectedOptions) {
        assert descriptionParser != null;
        if (this.argument.length() == 0) {
            throw new IllegalCommandArgumentException(this.commandType, descriptionParser, expectedOptions);
        }

        // Create a pattern to match all options and end of string
        StringBuilder endDelimeter = new StringBuilder("(?:$");
        for (CommandOption<?> option : expectedOptions) {
            endDelimeter.append("| /");
            endDelimeter.append(option.getOption());
        }
        endDelimeter.append(")");
        String end = endDelimeter.toString();

        // Parse options
        // All options should start with /option_name <option_value>
        // Find the smallest option string index to get the description
        int minStartMatch = this.argument.length();
        for (CommandOption<?> option : expectedOptions) {
            try {
                int matchStart = option.parse(this.argument, end);
                minStartMatch = Math.min(minStartMatch, matchStart);
            } catch (IllegalCommandException e) {
                throw e;
            } catch (IllegalArgumentException e) {
                throw new IllegalCommandArgumentException(this.commandType, descriptionParser, expectedOptions);
            }
        }

        // Get description
        String description = this.argument.substring(0, minStartMatch).trim();
        if (description.length() == 0) {
            throw new IllegalCommandArgumentException(this.commandType, descriptionParser, expectedOptions);
        }

        try {
            return descriptionParser.parse(description);
        } catch (Exception e) {
            // Handle parsing exceptions by re-throwing as IllegalCommandArgumentException
            throw new IllegalCommandArgumentException(this.commandType, descriptionParser, expectedOptions);
        }
    }

    public CommandType getCommandType() {
        return this.commandType;
    }
}
