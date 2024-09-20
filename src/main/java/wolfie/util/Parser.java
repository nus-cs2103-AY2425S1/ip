package wolfie.util;
import wolfie.command.AddDeadlineCommand;
import wolfie.command.AddEventCommand;
import wolfie.command.AddTodoCommand;
import wolfie.command.Command;
import wolfie.command.DeleteCommand;
import wolfie.command.ExitCommand;
import wolfie.command.FindCommand;
import wolfie.command.ListCommand;
import wolfie.command.MarkCommand;
import wolfie.command.OnCommand;
import wolfie.command.UnmarkCommand;
import wolfie.exception.WolfieException;

/**
 * Represents a parser that handles the parsing of user input.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input User input.
     * @return Command corresponding to the user input.
     * @throws WolfieException If the user input is invalid.
     */
    public static Command parse(String input) throws WolfieException {
        String[] parts = input.split(" ", 2); // Split the input into two parts
        String commandWord = parts[0]; // First part is the command word
        String arguments = parts.length > 1 ? parts[1] : ""; // Second part is the arguments

        return switch (commandWord) {
        case "bye" -> {
            if (!arguments.isEmpty()) {
                throw new WolfieException("⚠ The 'bye' command should not have any arguments.");
            }
            yield new ExitCommand();
        }
        case "list" -> {
            if (!arguments.isEmpty()) {
                throw new WolfieException("⚠ The 'list' command should not have any arguments.");
            }
            yield new ListCommand();
        }
        case "mark" -> new MarkCommand(arguments);
        case "unmark" -> new UnmarkCommand(arguments);
        case "todo" -> new AddTodoCommand(arguments);
        case "deadline" -> new AddDeadlineCommand(arguments);
        case "event" -> new AddEventCommand(arguments);
        case "delete" -> new DeleteCommand(arguments);
        case "on" -> new OnCommand(arguments);
        case "find" -> new FindCommand(arguments);
        default -> throw new WolfieException("""
                I'm sorry Dean's Lister, but I don't know what that means :-(
                These are the commands I understand:
                1. todo
                2. deadline
                3. event
                4. mark
                5. unmark
                6. delete
                7. find
                8. on
                9. list
                10. bye
                """);
        };
    }
}
