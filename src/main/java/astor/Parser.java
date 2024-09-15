package astor;

import astor.command.Command;
import astor.command.DeadlineCommand;
import astor.command.DeleteCommand;
import astor.command.EventCommand;
import astor.command.ExitCommand;
import astor.command.FindCommand;
import astor.command.ListCommand;
import astor.command.MarkCommand;
import astor.command.TodoCommand;
import astor.command.UnmarkCommand;
import astor.exception.AstorException;
import astor.exception.EmptyInputException;
import astor.exception.UnspecificTaskException;

/**
 * Parses user input and categorises the command to take.
 */
public class Parser {

    /**
     * The list of the available commands.
     */
    private enum Action {
        BYE,
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        EMPTY,
        DEFAULT,
        FIND
    }

    public Parser() {
    }

    /**
     * Scans the user input for the first keyword to deduce the command given.
     *
     * @param input the user input string to categorise
     * @return the action categorised from the input
     */
    private static Action categorise(String input) {
        assert input != null : "input cannot be null";
        if (input.equals("bye")) {
            return Action.BYE;
        } else if (input.startsWith("mark")) {
            return Action.MARK;
        } else if (input.startsWith("unmark")) {
            return Action.UNMARK;
        } else if (input.equals("list")) {
            return Action.LIST;
        } else if (input.startsWith("todo")) {
            return Action.TODO;
        } else if (input.startsWith("deadline")) {
            return Action.DEADLINE;
        } else if (input.startsWith("event")) {
            return Action.EVENT;
        } else if (input.startsWith("delete")) {
            return Action.DELETE;
        } else if (input.isEmpty()) {
            return Action.EMPTY;
        } else if (input.startsWith("find")) {
            return Action.FIND;
        }
        return Action.DEFAULT;
    }

    /**
     * Processes the user input and returns the corresponding Command object.
     *
     * @param input the user input string to process
     * @return the Command object corresponding to the input
     * @throws AstorException if the input is invalid or results in an error
     * @throws EmptyInputException if the input is empty
     * @throws UnspecificTaskException if the input does not match any known command
     */
    public static Command process(String input) throws AstorException {
        assert input != null : "input cannot be null";
        Action action = categorise(input);
        return switch (action) {
        case MARK -> new MarkCommand(input);
        case UNMARK -> new UnmarkCommand(input);
        case LIST -> new ListCommand();
        case DELETE -> new DeleteCommand(input);
        case TODO -> new TodoCommand(input);
        case DEADLINE -> new DeadlineCommand(input);
        case EVENT -> new EventCommand(input);
        case EMPTY -> throw new EmptyInputException();
        case BYE -> new ExitCommand();
        case FIND -> new FindCommand(input);
        default -> throw new UnspecificTaskException();
        };
    }
}
