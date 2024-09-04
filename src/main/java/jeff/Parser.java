package jeff;


import jeff.command.Command;
import jeff.command.DeadlineCommand;
import jeff.command.DeleteCommand;
import jeff.command.EventCommand;
import jeff.command.ExitCommand;
import jeff.command.FindCommand;
import jeff.command.ListCommand;
import jeff.command.MarkCommand;
import jeff.command.PrintCommand;
import jeff.command.ToDoCommand;
import jeff.command.UnMarkCommand;
import jeff.exceptions.JeffException;

/**
 * Parses user input and returns the appropriate command.
 * The Parser class is responsible for interpreting user commands and returning the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user's command input and returns the corresponding Command object.
     *
     * @param fullCommand The full command string input by the user.
     * @return The Command object corresponding to the user's input.
     * @throws JeffException If the command is not recognized.
     */
    public static Command parse(String fullCommand) throws JeffException {
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
        case "find":
            return new FindCommand(args);

        // Add more cases here for other commands
        default:
            throw new JeffException("Unknown command!");
        }
    }
}
