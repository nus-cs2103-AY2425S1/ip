package parser;

import commands.*;
import exceptions.InputException;

public class Parser {
    public static Command parse(String fullCommand) throws InputException {
        String[] commandParts = fullCommand.trim().split(" ", 2);
        String command = commandParts[0].toLowerCase();

        return switch (command) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            case "unmark" -> new UnmarkCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            case "delete" -> new DeleteCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            case "todo" -> new AddCommand("todo", commandParts.length > 1 ? commandParts[1].trim() : "");
            case "deadline" -> new AddCommand("deadline", commandParts.length > 1 ? commandParts[1].trim() : "");
            case "event" -> new AddCommand("event", commandParts.length > 1 ? commandParts[1].trim() : "");
            case "help" -> new HelpCommand();
            default -> throw new InputException("Invalid command. Type 'help' for assistance.");
        };
    }
}
