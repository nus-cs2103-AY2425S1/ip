package jeff;

import jeff.command.*;
import jeff.exceptions.JEFFException;

public class Parser {

    public static Command parse(String fullCommand) throws JEFFException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "print":
            return new PrintCommand(args);
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnMarkCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "todo":
            return new ToDoCommand(args);
        case "deadline":
            return new DeadlineCommand(args);
        case "event":
            return new EventCommand(args);

        // Add more cases here for other commands
        default:
            throw new JEFFException("Unknown command!"); // Implement UnknownCommand class to handle unrecognized commands
        }
    }
}
