package darkpool.parser;

import darkpool.DarkpoolException;
import darkpool.command.Command;

/**
 * Parser class is responsible for parsing user input and returning the appropriate Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object.
     *
     * @param input User input.
     * @return Command object.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static Command parse(String input) throws DarkpoolException {
        String[] userInput = prepareUserInput(input);
        return parseCommand(userInput);
    }

    /**
     * Parses the user input and returns the appropriate Command object.
     *
     * @param userInput User input.
     * @return Command object.
     * @throws DarkpoolException If the user input is invalid.
     */
    private static Command parseCommand(String[] userInput) throws DarkpoolException {
        switch (userInput[0]) {
        case "bye" -> {
            return ExitParser.parse();
        }
        case "list" -> {
            return ListParser.parse();
        }
        case "mark" -> {
            return MarkParser.parse(userInput);
        }
        case "unmark" -> {
            return UnmarkParser.parse(userInput);
        }
        case "delete" -> {
            return DeleteParser.parse(userInput);
        }
        case "find" -> {
            return FindParser.parse(userInput);
        }
        case "todo" -> {
            return TodoParser.parse(userInput);
        }
        case "deadline" -> {
            return DeadlineParser.parse(userInput);
        }
        case "after" -> {
            return AfterParser.parse(userInput);
        }
        case "event" -> {
            return EventParser.parse(userInput);
        }
        default -> throw new DarkpoolException("what???");
        }
    }

    /**
     * Prepares the user input by splitting it into two parts.
     *
     * @param input User input.
     * @return Array containing the command and the description.
     */
    private static String[] prepare(String input) {
        String[] userInput = input.split(" ", 2);
        if (userInput.length == 2) {
            userInput[1] = userInput[1].trim();
        }
        return userInput;
    }

    /**
     * Prepares the user input by splitting it into two parts.
     *
     * @param input User input.
     * @return Array containing the command and the description.
     */
    private static String[] prepareUserInput(String input) {
        return prepare(input);
    }

}
