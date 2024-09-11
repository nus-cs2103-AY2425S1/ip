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
     * based on the input. The command is determined by the first word in the input.
     *
     * Supported commands include:
     * - "mark", "unmark"
     * - "todo"
     * - "deadline"
     * - "event"
     * - "delete"
     * - "list"
     * - "find"
     * - "bye"
     *
     * @param userInput The input provided by the user.
     * @param tasks The current list of tasks.
     * @param ui The user interface to interact with the user.
     * @return The {@link Command} corresponding to the user's input.
     * @throws UnknownCommandException If the input does not match any known command.
     */
    public static Command parseCommand(String userInput, TaskList tasks, Ui ui) throws UnknownCommandException {
        assert userInput != null : "User input should not be null";
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";

        userInput = userInput.trim();
        if (userInput.isEmpty()) {
            throw new UnknownCommandException();
        }

        String[] inputParts = userInput.split(" ", 2);
        String commandWord = inputParts[0].toLowerCase();

        switch (commandWord) {
        case "mark", "unmark":
            return new MarkCommand(userInput, tasks, ui);
        case "todo":
            return new TodoCommand(userInput, tasks, ui);
        case "deadline":
            return new DeadlineCommand(userInput, tasks, ui);
        case "event":
            return new EventCommand(userInput, tasks, ui);
        case "delete":
            return new DeleteCommand(userInput, tasks, ui);
        case "list":
            return new ListCommand(userInput, tasks, ui);
        case "find":
            return new FindCommand(userInput, tasks, ui);
        case "bye":
            return new ByeCommand(userInput, tasks, ui);
        default:
            assert false : "Unhandled command word" + commandWord;
            throw new UnknownCommandException();
        }
    }
}
