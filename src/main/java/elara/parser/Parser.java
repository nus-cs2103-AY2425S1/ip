package elara.parser;

import elara.command.*;
;

import elara.task.InvalidInputException;

public class Parser {
    public static String getCommandType(String input) {
        String[] parts = input.split(" ", 2);
        return parts[0].toLowerCase();
    }
    public static Command parse(String input) throws InvalidInputException {
        String command = getCommandType(input);
        return switch (command) {
            case "bye" -> new ExitCommand();
            case "find" -> new FindCommand(input);
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(input);
            case "unmark" -> new UnmarkCommand(input);
            case "delete" -> new DeleteCommand(input);
            case "todo", "deadline", "event" -> new AddCommand(command, input);
            default -> throw new InvalidInputException("Errrrrrrr... I don't know what you are trying to say...\n" +
                    "Try one of our commands: list mark unmark bye deadline todo event");
        };
    }
}
