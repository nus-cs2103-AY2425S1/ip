package purrfessordipsy.parser;

import purrfessordipsy.command.*;
import purrfessordipsy.exception.UnknownCommandException;
import purrfessordipsy.tasklist.TaskList;
import purrfessordipsy.ui.Ui;

/**
 * The Parser class is responsible for interpreting user input and returning the corresponding command.
 * It parses the user input string and determines which command to create based on the input.
 * The command is returned as an object that can be executed.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object, which can then be executed.
     * The method identifies the command based on the first word of the user input and creates the
     * corresponding Command object.
     *
     * @param userInput The full input string provided by the user.
     * @param tasks The task list that the command will operate on.
     * @param ui The UI handler for interacting with the user.
     * @return The appropriate Command object based on the user's input.
     * @throws UnknownCommandException If the user input does not match any recognized commands.
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
            case "bye" -> new ByeCommand(userInput, tasks, ui);
            default -> throw new UnknownCommandException();
        };
    }
}
