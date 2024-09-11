package dipsy.parser;

import dipsy.command.ByeCommand;
import dipsy.command.Command;
import dipsy.command.DeadlineCommand;
import dipsy.command.DeleteCommand;
import dipsy.command.EventCommand;
import dipsy.command.FindCommand;
import dipsy.command.ListCommand;
import dipsy.command.MarkCommand;
import dipsy.command.TodoCommand;
import dipsy.exception.UnknownCommandException;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * The {@code Parser} class is responsible for interpreting user input and returning
 * the appropriate {@link Command} object. It handles the parsing of input to identify
 * what type of command the user intends to execute.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding {@link Command} object
     * based on the first word in the input.
     * <p></p>
     * Supported commands include:
     * <ul>
     *     <li>mark, unmark</li>
     *     <li>todo</li>
     *     <li>deadline</li>
     *     <li>event</li>
     *     <li>delete</li>
     *     <li>list</li>
     *     <li>find</li>
     *     <li>bye</li>
     * </ul>
     *
     * @param userInput The input provided by the user.
     * @param tasks The current list of tasks.
     * @param ui The user interface to interact with the user.
     * @return The {@link Command} corresponding to the user's input.
     * @throws UnknownCommandException If the input does not match any known command.
     */
    public static Command parseCommand(String userInput, TaskList tasks, Ui ui) throws UnknownCommandException {
        String[] inputParts = userInput.split(" ", 2);
        String commandWord = inputParts[0].toLowerCase();

        return switch (commandWord) {
        case "mark", "unmark" -> new MarkCommand(userInput, tasks, ui);
        case "todo" -> new TodoCommand(userInput, tasks, ui);
        case "deadline" -> new DeadlineCommand(userInput, tasks, ui);
        case "event" -> new EventCommand(userInput, tasks, ui);
        case "delete" -> new DeleteCommand(userInput, tasks, ui);
        case "list" -> new ListCommand(userInput, tasks, ui);
        case "find" -> new FindCommand(userInput, tasks, ui);
        case "bye" -> new ByeCommand(userInput, tasks, ui);
        default -> throw new UnknownCommandException();
        };
    }
}
