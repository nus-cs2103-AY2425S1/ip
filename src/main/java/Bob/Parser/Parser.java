package bob.parser;

import bob.command.*;
import bob.exception.BobException;

/**
 * Parses user input and convert it into commands that are executable.
 * Parser class interprets user input, and assigns to to its corresponding
 * command object.
 */
public class Parser {

    /**
     * Parses the input string, returns the corresponding command object.
     * This method checks the input, splits into separate parts, identifies command type and constructs
     * the corresponding command object.
     * @param input the raw input string from user
     * @return the Command object that corresponds to user's input.
     * @throws BobException when the command is of the wrong format or when the command is unrecognized.
     */
    public Command parse(String input) throws BobException {
        assert input != null : "Input should not be null";

        String[] inputParts = input.split(" ", 2);
        assert inputParts.length > 0 : "There should be at least one part in the input";

        String command = inputParts[0].toLowerCase();
        String taskDescription = (inputParts.length == 1) ? "" : inputParts[1];

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
        case "update":
            int index = Integer.parseInt(inputParts[1].split(" ")[0]) - 1;
            String newDetails = inputParts[1].split(" ", 2)[1];
            return new UpdateCommand(index, newDetails);
        case "bye":
            return new ExitCommand();
        default:
            throw new BobException("Bob does not understand that Command, sorry :(");
        }
    }

    /**
     * Parses the task index from the given task description string.
     * This method extracts the task index from the input.
     *
     * @param taskDescription the task description string containing task index.
     * @return the task index as an integer.
     * @throws BobException when the task index is not valid.
     */
    private int parseTaskIndex(String taskDescription) throws BobException {
        assert taskDescription != null : "Task description should not be null";
        try {
            return Integer.parseInt(taskDescription.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BobException("Invalid task index. Please enter a valid number.");
        }
    }

}

