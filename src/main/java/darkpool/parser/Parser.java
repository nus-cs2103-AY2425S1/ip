package darkpool.parser;

import darkpool.command.Command;
import darkpool.util.DarkpoolException;

/**
 * The Parser class is responsible for parsing user input and returning the appropriate Command object.
 */
public class Parser {

    public enum CommandType {
        Bye, List, Mark, Unmark, Delete, Find, Todo, Deadline, Event
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input to be parsed.
     * @return The Command object corresponding to the user input.
     * @throws DarkpoolException If the user input is invalid or cannot be parsed.
     */
    public static Command parse(String input) throws DarkpoolException {
        String[] userInput = input.split(" ", 2);

        if (userInput.length == 2) {
            userInput[1] = userInput[1].trim();
        }

        return switch (userInput[0]) {
            case "bye" -> ExitParser.parse();
            case "list" -> ListParser.parse();
            case "mark" -> MarkParser.parse(userInput);
            case "unmark" -> UnmarkParser.parse(userInput);
            case "delete" -> DeleteParser.parse(userInput);
            case "find" -> FindParser.parse(userInput);
            case "todo" -> TodoParser.parse(userInput);
            case "deadline" -> DeadlineParser.parse(userInput);
            case "event" -> EventParser.parse(userInput);
            default -> throw new DarkpoolException("what???");
        };
    }

}
