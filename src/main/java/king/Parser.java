package king;

import king.commands.*;

public class Parser {
    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "list":
                return new ListCommand();
            case "delete":
                return new DeleteCommand(parts[1]);
            case "mark":
                return new MarkCommand(parts[1]);
            case "unmark":
                return new UnmarkCommand(parts[1]);
            case "todo":
                return new TodoCommand(parts[1]);
            case "deadline":
                return new DeadlineCommand(parts[1]);
            case "event":
                return new EventCommand(parts[1]);
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand();
        }
    }
}
