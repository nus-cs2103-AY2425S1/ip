package purrfessordipsy.parser;

import purrfessordipsy.command.*;
import purrfessordipsy.exception.UnknownCommandException;
import purrfessordipsy.tasklist.TaskList;
import purrfessordipsy.ui.Ui;

public class Parser {
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
