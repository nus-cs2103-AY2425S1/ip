package king;

import king.commands.*;

/**
 * The Parser class interprets the user's input command and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user's input command and returns the appropriate Command object.
     *
     * @param fullCommand The full command input by the user.
     * @return A Command object corresponding to the user's input.
     */
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
        case "find":
            return new FindCommand(parts[1]);
        case "remind":
            return new ReminderCommand(parts[1]);
        default:
            return new InvalidCommand();
        }
    }
}
