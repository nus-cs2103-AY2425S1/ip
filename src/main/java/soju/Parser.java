package soju;

import soju.commands.*;

/**
 * The {@code Parser} class is responsible for parsing user input and returning the appropriate command.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding {@code Command} object.
     *
     * @param input The user input string.
     * @return The command that corresponds to the user input.
     * @throws SojuException If the command is not recognized.
     */
    public static Command parse(String input) throws SojuException {
        String command = input.split(" ")[0];
        return switch (command) {
            case "bye" -> new ByeCommand(input);
            case "list" -> new ListCommand(input);
            case "mark" -> new MarkCommand(input);
            case "unmark" -> new UnmarkCommand(input);
            case "delete" -> new DeleteCommand(input);
            case "todo" -> new TodoCommand(input);
            case "deadline" -> new DeadlineCommand(input);
            case "event" -> new EventCommand(input);
            default -> throw new SojuException("Unknown command, type help to see a list of commands");
        };
    }
}
