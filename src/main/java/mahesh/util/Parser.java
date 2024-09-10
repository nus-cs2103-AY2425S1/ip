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
        StringTokenizer tokenizedInput = new StringTokenizer(originalInput);
        String commandString = tokenizedInput.nextToken();
        CommandNames command = CommandNames.fromString(commandString);
        switch (command) {
        case LIST:
            return new PrintCommand(list);
        case MARK:
            try {
                return new MarkCommand(list, store, Integer.parseInt(tokenizedInput.nextToken()) - 1, true);
            } catch (NumberFormatException err) {
                Ui.printIncompleteCommandErr(
                    new MaheshException("Please follow the given format: mark <index>, index must be an integer")
                );
                return null;
            }
        case UNMARK:
            try {
                return new MarkCommand(list, store, Integer.parseInt(tokenizedInput.nextToken()) - 1, false);
            } catch (NumberFormatException err) {
                return new IncompleteCommand(
                    "Please follow the given format: unmark <index>, index must be an integer"
                );
            }
        case TODO:
            try {
                Todo todo = Todo.parseTodo(tokenizedInput);
                return new AddCommand(list, store, todo);
            } catch (MaheshException err) {
                return new IncompleteCommand(err.getMessage());
            }
        case DEADLINE:
            try {
                Deadline deadline = Deadline.parseDeadline(tokenizedInput);
                return new AddCommand(list, store, deadline);
            } catch (MaheshException err) {
                return new IncompleteCommand(err.getMessage());
            }
        case EVENT:
            try {
                Event event = Event.parseEvent(tokenizedInput);
                return new AddCommand(list, store, event);
            } catch (MaheshException err) {
                return new IncompleteCommand(err.getMessage());
            }
        case DELETE:
            try {
                return new DeleteCommand(list, store, Integer.parseInt(tokenizedInput.nextToken()) - 1);
            } catch (NumberFormatException err) {
                return new IncompleteCommand(
                    "Please follow the given format: delete <index>, index must be an integer"
                );
            }
        case FIND:
            try {
                return new FindCommand(list, tokenizedInput.nextToken());
            } catch (NumberFormatException err) {
                return new IncompleteCommand("Please follow the given format: find <search_term>");
            }
        default:
            return null;
        }
    }
}
