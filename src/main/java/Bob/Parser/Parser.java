package bob.parser;

import bob.command.*;
import bob.exception.BobException;

public class Parser {
    public Command parse(String input) throws BobException {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0].toLowerCase();
        String taskDescription = (inputParts.length <= 1) ? "" : inputParts[1];

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseTaskIndex(taskDescription));
        case "unmark":
            return new UnmarkCommand(parseTaskIndex(taskDescription));
        case "todo":
            return new TodoCommand(taskDescription);
        case "deadline":
            return new DeadlineCommand(taskDescription);
        case "event":
            return new EventCommand(taskDescription);
        case "delete":
            return new DeleteCommand(parseTaskIndex(taskDescription));
        case "find":
            return new FindCommand(taskDescription);
        case "bye":
            return new ExitCommand();
        default:
            throw new BobException("Bob does not understand that command, sorry :(");
        }
    }
    private int parseTaskIndex(String taskDescription) throws BobException {
        try {
            return Integer.parseInt(taskDescription.trim()) - 1;  //convert to 0-based index
        } catch (NumberFormatException e) {
            throw new BobException("Invalid task index. Please enter a valid number.");
        }
    }
}

