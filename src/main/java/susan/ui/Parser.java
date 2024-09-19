package susan.ui;

import susan.command.*;

public class Parser {
    /**
     * Parses user input into commands.
     *
     * @param fullCommand full input command from user.
     * @throws SusanException if command is invalid.
     */
    public static Command parse(String fullCommand) throws SusanException {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandWord = commandParts[0];

        switch (commandWord) {
        case "hello":
            return new HelloCommand();
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(commandParts);
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(commandParts);
        case "mark":
            return new MarkCommand(commandParts, true);
        case "unmark":
            return new MarkCommand(commandParts, false);
        case "find":
            return new FindCommand(commandParts);
        case "remindme":
            return new RemindCommand();
        case "bye":
            return new ExitCommand();
        default:
            throw new SusanException("You cry a lot and you are not productive! Please give a valid command.");
        }
    }
}
