/**
 * Parser represents the parsing of input prompt and determine which command to execute
 */
public class Parser {

    /**
     * Parse the giving input and determine the corresponding commands
     *
     * @param prompt The input from the user
     * @return A command object representing the operation requested by the user
     */
    public static Command parse(String prompt) {

        String[] dissectedPrompt = prompt.trim().split(" ", 2);
        String command = dissectedPrompt[0];
        String arguments = null;
        if (dissectedPrompt.length == 2) {
            arguments = dissectedPrompt[1].trim();
        }

        switch (command) {
        case ByeCommand.COMMAND_PREFIX:
            return new ByeCommand(arguments);
        case ListCommand.COMMAND_PREFIX:
            return new ListCommand(arguments);
        case MarkCommand.COMMAND_PREFIX:
            return new MarkCommand(arguments);
        case UnmarkCommand.COMMAND_PREFIX:
            return new UnmarkCommand(arguments);
        }

        return null;
    }

}
