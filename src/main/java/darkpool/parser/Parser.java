package darkpool.parser;

import darkpool.command.Command;
import darkpool.DarkpoolException;

public class Parser {

    public static Command parse(String input) throws DarkpoolException {
        String[] userInput = prepareUserInput(input);
        return parseCommand(userInput);
    }

    private static Command parseCommand(String[] userInput) throws DarkpoolException {
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

    private static String[] prepare(String input) {
        String[] userInput = input.split(" ", 2);
        if (userInput.length == 2) {
            userInput[1] = userInput[1].trim();
        }
        return userInput;
    }

    private static String[] prepareUserInput(String input) {
        return prepare(input);
    }

}
