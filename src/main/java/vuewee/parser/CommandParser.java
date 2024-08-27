package vuewee.parser;

import vuewee.command.CommandType;

/**
 * The CommandParser class is responsible for parsing and extracting information
 * from a command input string. It supports parsing commands with or without a
 * description, and with or without an integer parameter.
 */
public class CommandParser {
    private String argument; // Input text after command title

    private CommandType commandType;

    // Only description or integer description is allowed
    private String description;
    private int intParam;

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
     * @param hasDescription Whether the command has a description.
     */
    public void parse(boolean hasDescription) {
        this.parse(hasDescription, false);
    }

    /**
     * Parses the input string to extract the description as an integer parameter
     * when isIntegerDescription is set to true.
     * 
     * @param hasDescription       Whether the command has a description.
     * @param isIntegerDescription Whether the description is an integer.
     */
    public void parse(boolean hasDescription, boolean isIntegerDescription) {
        this.parse(hasDescription, isIntegerDescription, new CommandOption[0]);
    }

    /**
     * Parses the input string and extracts the description. Uses expectedOptions to
     * parse the options in the input string.
     * 
     * @param hasDescription       Whether the command has a description.
     * @param isIntegerDescription Whether the description is an integer.
     * @param expectedOptions      The expected options to parse as a CommandOption
     *                             type.
     */
    public void parse(boolean hasDescription, boolean isIntegerDescription, CommandOption<?>... expectedOptions) {
        // Reset states
        this.description = "";

        if (hasDescription && this.argument.length() == 0) {
            throw new IllegalCommandArgumentException(this.commandType, hasDescription, isIntegerDescription,
                    expectedOptions);
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
        int minStartMatch = this.argument.length();
        for (CommandOption<?> option : expectedOptions) {
            try {
                int matchStart = option.parse(this.argument, end);
                minStartMatch = Math.min(minStartMatch, matchStart);
            } catch (IllegalCommandException e) {
                throw e;
            } catch (IllegalArgumentException e) {
                throw new IllegalCommandArgumentException(this.commandType, hasDescription, isIntegerDescription,
                        expectedOptions);
            }
        }

        // Get description
        String description = this.argument.substring(0, minStartMatch).trim();
        if (hasDescription && description.length() == 0) {
            throw new IllegalCommandArgumentException(this.commandType, hasDescription, isIntegerDescription,
                    expectedOptions);
        }
        this.description = description;

        if (isIntegerDescription) {
            try {
                this.intParam = Integer.parseInt(this.description);
            } catch (NumberFormatException e) {
                throw new IllegalCommandArgumentException(this.commandType, hasDescription, isIntegerDescription,
                        expectedOptions);
            }
        }
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public String getDescription() {
        return this.description;
    }

    public int getIntParam() {
        return this.intParam;
    }
}
