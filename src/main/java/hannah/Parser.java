package hannah;

import hannah.command.Command;

/**
 * The Parser class handles the parsing of user input into commands.
 */
public class Parser {

    /**
     * Parses the user's input string and returns a Command object representing the command.
     *
     * @param userInput The raw input string from the user.
     * @return A Command object corresponding to the parsed command type and arguments.
     */
    public Command parse(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String commandType = parts[0];

        switch (commandType) {
        case "bye":
            return new Command("bye");
        case "list":
            return new Command("list");
        case "mark":
            return new Command("mark", parts.length > 1 ? parts[1] : null);
        case "unmark":
            return new Command("unmark", parts.length > 1 ? parts[1] : null);
        case "delete":
            return new Command("delete", parts.length > 1 ? parts[1] : null);
        case "todo":
            return new Command("todo", parts.length > 1 ? parts[1] : null);
        case "deadline":
            return new Command("deadline", parts.length > 1 ? parts[1] : null);
        case "event":
            return new Command("event", parts.length > 1 ? parts[1] : null);
        case "find":
            return new Command("find", parts.length > 1 ? parts[1] : null);
        case "help":
            return new Command("help", parts.length > 1 ? parts[1] : null);
        default:
            return new Command("unknown");
        }
    }
}
