package soju;

import soju.commands.*;

public class Parser {
    public static Command parse(String input) throws SojuException {
        String command = input.split(" ")[0];
        switch (command) {
            case "bye":
                return new ByeCommand(input);
            case "list":
                return new ListCommand(input);
            case "mark":
                return new MarkCommand(input);
            case "unmark":
                return new UnmarkCommand(input);
            case "delete":
                return new DeleteCommand(input);
            case "todo":
                return new TodoCommand(input);
            case "deadline":
                return new DeadlineCommand(input);
            case "event":
                return new EventCommand(input);
            case "find":
                return new FindCommand(input);
            default:
                throw new SojuException("Unknown command, type help to see a list of soju.commands");
        }
    }
}
