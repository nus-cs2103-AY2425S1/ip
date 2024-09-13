package mahesh.util;

import java.util.StringTokenizer;

import mahesh.command.AddCommand;
import mahesh.command.Command;
import mahesh.command.CommandNames;
import mahesh.command.DeleteCommand;
import mahesh.command.FindCommand;
import mahesh.command.IncompleteCommand;
import mahesh.command.MarkCommand;
import mahesh.command.PrintCommand;
import mahesh.task.Deadline;
import mahesh.task.Event;
import mahesh.task.Recurring;
import mahesh.task.Todo;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {

    private TaskList list;
    private Storage store;

    /**
     * Constructs a Parser with the specified TaskList and Storage.
     *
     * @param list  the TaskList to be managed by the commands
     * @param store the Storage to be updated by the commands
     */
    public Parser(TaskList list, Storage store) {
        this.list = list;
        this.store = store;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param originalInput the user input to be parsed
     * @return the Command object corresponding to the user input
     * @throws MaheshException if the input cannot be parsed into a valid command
     */
    public Command parse(String originalInput) throws MaheshException {
        assert originalInput != null : "Input should not be null";
        assert !originalInput.trim().isEmpty() : "Input should not be empty";

        StringTokenizer tokenizedInput = new StringTokenizer(originalInput);
        assert tokenizedInput.hasMoreTokens() : "Input should contain at least one token";

        String commandString = tokenizedInput.nextToken();
        CommandNames command = CommandNames.fromString(commandString);
        assert command != null : "Command should not be null";

        switch (command) {
        case LIST:
            return new PrintCommand(list);
        case MARK:
            return parseMarkCommand(tokenizedInput, true);
        case UNMARK:
            return parseMarkCommand(tokenizedInput, false);
        case TODO:
            return parseTodoCommand(tokenizedInput);
        case DEADLINE:
            return parseDeadlineCommand(tokenizedInput);
        case EVENT:
            return parseEventCommand(tokenizedInput);
        case DELETE:
            return parseDeleteCommand(tokenizedInput);
        case FIND:
            return parseFindCommand(tokenizedInput);
        case RECURRING:
            return parseRecurringCommand(tokenizedInput);
        default:
            return null;
        }
    }

    private Command parseMarkCommand(StringTokenizer tokenizedInput, boolean isMark) {
        try {
            return new MarkCommand(list, store, Integer.parseInt(tokenizedInput.nextToken()) - 1, isMark);
        } catch (NumberFormatException err) {
            return new IncompleteCommand(
                String.format("Please follow the given format: %s <index>, index must be an integer",
                    isMark ? "mark" : "unmark")
            );
        }
    }

    private Command parseTodoCommand(StringTokenizer tokenizedInput) {
        try {
            Todo todo = Todo.parseTodo(tokenizedInput);
            return new AddCommand(list, store, todo);
        } catch (MaheshException err) {
            return new IncompleteCommand(err.getMessage());
        }
    }

    private Command parseDeadlineCommand(StringTokenizer tokenizedInput) {
        try {
            Deadline deadline = Deadline.parseDeadline(tokenizedInput);
            return new AddCommand(list, store, deadline);
        } catch (MaheshException err) {
            return new IncompleteCommand(err.getMessage());
        }
    }

    private Command parseEventCommand(StringTokenizer tokenizedInput) {
        try {
            Event event = Event.parseEvent(tokenizedInput);
            return new AddCommand(list, store, event);
        } catch (MaheshException err) {
            return new IncompleteCommand(err.getMessage());
        }
    }

    private Command parseDeleteCommand(StringTokenizer tokenizedInput) {
        try {
            return new DeleteCommand(list, store, Integer.parseInt(tokenizedInput.nextToken()) - 1);
        } catch (NumberFormatException err) {
            return new IncompleteCommand(
                "Please follow the given format: delete <index>, index must be an integer"
            );
        }
    }

    private Command parseFindCommand(StringTokenizer tokenizedInput) {
        try {
            return new FindCommand(list, tokenizedInput.nextToken());
        } catch (NumberFormatException err) {
            return new IncompleteCommand("Please follow the given format: find <search_term>");
        }
    }

    private Command parseRecurringCommand(StringTokenizer tokenizedInput) {
        try {
            Recurring recurring = Recurring.parseRecurring(tokenizedInput);
            return new AddCommand(list, store, recurring);
        } catch (MaheshException err) {
            return new IncompleteCommand(err.getMessage());
        }
    }
}
