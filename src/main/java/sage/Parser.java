package sage;

import sage.Command.*;

public class Parser {
    public static Command parse(String input) throws SageException {
        String[] fullCommand = input.split(" ", 2);
        String commandType = fullCommand[0];

        return switch (commandType) {
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(fullCommand[1], true);
            case "unmark" -> new MarkCommand(fullCommand[1], false);
            case "todo" -> new AddCommand("todo", fullCommand[1]);
            case "deadline" -> new AddCommand("deadline", fullCommand[1]);
            case "event" -> new AddCommand("event", fullCommand[1]);
            case "find" -> new FindCommand(fullCommand[1]);
            case "delete" -> new DeleteCommand(fullCommand[1]);
            case "bye" -> new ExitCommand();
            default -> throw new SageException("Invalid Command");
        };
    }
}
