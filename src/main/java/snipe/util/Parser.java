package snipe.util;

import snipe.command.*;
import snipe.exception.SnipeException;
import snipe.task.Deadline;
import snipe.task.Event;
import snipe.task.ToDo;

/**
 * The {@code Parser} class handles parsing of user input into executable commands.
 * It interprets the input string and determines the appropriate command to execute.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding {@link Command} object.
     * The method identifies the type of command from the user input and creates the appropriate
     * command object for execution.
     *
     * @param userInput The input provided by the user.
     * @return The {@link Command} object corresponding to the user input.
     * @throws SnipeException If the input is invalid or cannot be parsed into a known command.
     */
    public static Command parse(String userInput) throws SnipeException {
        String[] split = userInput.split(" ", 2);
        String commandWord = split[0].toUpperCase();

        switch (commandWord) {
        case "TODO":
        case "DEADLINE":
        case "EVENT":
            return parseTaskCommand(commandWord, split);
        case "DELETE":
            if (split.length < 2 || split[1].trim().isEmpty()) {
                throw new SnipeException("Please input a number. Use 'help for correct syntax");
            }
            return new DeleteCommand(Integer.parseInt(split[1]) - 1);
        case "MARK":
            if (split.length < 2 || split[1].trim().isEmpty()) {
                throw new SnipeException("Please input a number. Use 'help for correct syntax");
            }
            return new MarkCommand(Integer.parseInt(split[1]) - 1);
        case "UNMARK":
            if (split.length < 2 || split[1].trim().isEmpty()) {
                throw new SnipeException("Please input a number. Use 'help for correct syntax");
            }
            return new UnmarkCommand(Integer.parseInt(split[1]) - 1);
        case "HELP":
            return new HelpCommand();
        case "LIST":
            return new ListCommand();
        case "BYE":
            return new ExitCommand();
        default:
            throw new SnipeException("I'm sorry, I don't understand that command.");
        }
    }

    /**
     * Parses a task command and returns the corresponding {@link Command} object.
     * Handles the parsing of task-related commands such as "TODO", "DEADLINE", and "EVENT".
     *
     * @param commandWord The command word indicating the type of task (e.g., "TODO", "DEADLINE", "EVENT").
     * @param split       The split array of the user input, containing the command word and task details.
     * @return The {@link Command} object representing the task-related action.
     * @throws SnipeException If the input is invalid, such as missing details or incorrect format.
     */
    private static Command parseTaskCommand(String commandWord, String[] split) throws SnipeException {
        if (split.length < 2 || split[1].trim().isEmpty()) {
            throw new SnipeException("The description of a task cannot be empty. Use 'help' for correct syntax.");
        }

        String taskDescription = split[1];
        switch (commandWord) {
        case "TODO":
            return new AddCommand(new ToDo(taskDescription));
        case "DEADLINE":
            String[] deadlineSplit = taskDescription.split(" /by ", 2);
            if (deadlineSplit.length < 2) {
                throw new SnipeException("A deadline requires a description and a /by date.");
            }
            return new AddCommand(new Deadline(deadlineSplit[0], deadlineSplit[1]));
        case "EVENT":
            String[] eventSplit = taskDescription.split(" /from | /to ");
            if (eventSplit.length < 3) {
                throw new SnipeException("An event requires a description, a /from date, and a /to date.");
            }
            return new AddCommand(new Event(eventSplit[0], eventSplit[1], eventSplit[2]));
        default:
            throw new SnipeException("Invalid task type.");
        }
    }
}
