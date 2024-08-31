package bean.parser;

import bean.command.Command;
import bean.exception.UnknownCommandException;

/**
 * Parses user input and generates the appropriate Command object.
 */
public class Parser {

    /**
     * Parses the user's input string and returns a Command object.
     * If the command is not recognised, throws an UnknownCommandException.
     *
     * @param input The user's input string.
     * @return A Command object corresponding to the user's input.
     * @throws UnknownCommandException If the command is not recognised.
     */
    public Command parseCommand(String input) throws UnknownCommandException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "list":
                return new Command("list");
            case "mark":
                return new Command("mark", details);
            case "unmark":
                return new Command("unmark", details);
            case "delete":
                return new Command("delete", details);
            case "todo":
                return new Command("todo", details);
            case "deadline":
                return new Command("deadline", details);
            case "event":
                return new Command("event", details);
            case "find":
                return new Command("find", details);
            default:
                throw new UnknownCommandException();
        }
    }
}
