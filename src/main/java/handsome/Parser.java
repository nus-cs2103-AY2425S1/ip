package handsome;

import handsome.command.ByeCommand;
import handsome.command.Command;
import handsome.command.DeadlineCommand;
import handsome.command.DeleteCommand;
import handsome.command.EventCommand;
import handsome.command.FindCommand;
import handsome.command.ListCommand;
import handsome.command.MarkCommand;
import handsome.command.PromptCommand;
import handsome.command.TodoCommand;
import handsome.command.UnmarkCommand;
import handsome.exception.HandsomeException;
import handsome.exception.InvalidSyntaxException;

/**
 * The Parser class is responsible for interpreting user input and
 * converting it into executable commands within the Handsome chatBot.
 * The class identifies the type of command based on user input and
 * returns the appropriate Command object for execution.
 */
public class Parser {
    /**
     * Parses the user input string and returns the corresponding Command
     * object to be executed by the chatBot.
     * The method identifies commands such as "bye", "prompt", "mark", "delete",
     * "unmark", "list", "deadline", "event", and "todo", and creates specific
     * command objects accordingly.
     *
     * @param input The user input string.
     * @return A Command object corresponding to the parsed user input.
     * @throws HandsomeException If the input does not match any recognized command.
     */
    public static Command parse(String input) throws HandsomeException {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("prompt")) {
            return new PromptCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("find ")) {
            return new FindCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("deadline ")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event ")) {
            return new EventCommand(input);
        } else if (input.startsWith("todo ")) {
            return new TodoCommand(input);
        } else {
            throw new InvalidSyntaxException();
        }
    }
}
