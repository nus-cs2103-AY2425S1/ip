package astor;

import astor.command.*;
import astor.exception.*;

public class Parser {

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

    private static Action categorise(String input) {
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

    public static Command process(String input) throws AstorException {
        Action action = categorise(input);
        switch (action) {
        case MARK:
            return new MarkCommand(input);
        case UNMARK:
            return new UnmarkCommand(input);
        case LIST:
            return new ListCommand();
        case DELETE:
            return new DeleteCommand(input);
        case TODO:
            return new TodoCommand(input);
        case DEADLINE:
            return new DeadlineCommand(input);
        case EVENT:
            return new EventCommand(input);
        case EMPTY:
            throw new EmptyInputException();
        case BYE:
            return new ExitCommand();
        case DEFAULT:
            throw new UnspecificTaskException();
        case FIND:
            return new FindCommand(input);
        }
        return null;
    }
}
