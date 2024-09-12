package donna.parse;

import donna.DonnaException;

/**
 * Parses user input (commands and arguments) into ParsedCommand objects.
 */
public class Parser {

    /**
     * Parses the given input string into a ParsedCommand object.
     * The input string is split into a command and arguments based on the first space.
     * Recognizes commands such as "bye", "list", "mark", "unmark", "delete", "todo",
     * "deadline", "event", and "find".
     *
     * @param input Input string containing the command and arguments.
     * @return A ParsedCommand object representing the parsed command and arguments.
     * @throws DonnaException If the command is invalid or not recognized.
     */
    public ParsedCommand parse(String input) throws DonnaException {
        assert input != null && !input.trim().isEmpty() :
                "Input should not be null or empty";

        String[] inputWords = input.split(" ", 2);
        String command = inputWords[0];
        String arguments = inputWords.length > 1 ? inputWords[1] : "";

        switch (command) {
        case "bye":
            return new ParsedCommand("exit");
        case "list":
            return new ParsedCommand("list");
        case "mark":
            return new ParsedCommand("mark", arguments);
        case "unmark":
            return new ParsedCommand("unmark", arguments);
        case "delete":
            return new ParsedCommand("delete", arguments);
        case "todo":
        case "deadline":
        case "event":
            return new ParsedCommand("add", command, arguments);
        case "find":
            return new ParsedCommand("find", arguments);
        default:
            throw DonnaException.invalidTaskType(command);
        }
    }
}
