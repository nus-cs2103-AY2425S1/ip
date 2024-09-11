package yoda;

import yoda.commands.ByeCommand;
import yoda.commands.Command;
import yoda.commands.DeadlineCommand;
import yoda.commands.DeleteCommand;
import yoda.commands.EventCommand;
import yoda.commands.FindCommand;
import yoda.commands.ListCommand;
import yoda.commands.MarkCommand;
import yoda.commands.TodoCommand;
import yoda.commands.UnmarkCommand;
import yoda.exceptions.YodaException;

/**
 * Parses user input and returns the appropriate command to be executed.
 */
public class Parser {
    /**
     * Processes the user's input and returns the corresponding command.
     *
     * @param input The user's input string.
     * @param tasks The current list of tasks.
     * @return The command to be executed based on the user's input.
     * @throws YodaException If the input is invalid or cannot be processed.
     */
    public Command handle(String input, TaskList tasks) throws YodaException {
        assert tasks != null : "Tasks list should not be null";
        String[] splitInput = input.split(" ", 2);
        String commandString = splitInput[0];
        Command command = null;

        switch (commandString) {
        case "":
            throw new YodaException("Hmm... You must speak to be heard...");
        case "bye":
            command = new ByeCommand();
            break;
        case "list":
            command = new ListCommand(tasks);
            break;
        case "mark":
            command = new MarkCommand(tasks, input);
            break;
        case "unmark":
            command = new UnmarkCommand(tasks, input);
            break;
        case "delete":
            command = new DeleteCommand(tasks, input);
            break;
        case "todo":
            command = new TodoCommand(tasks, input);
            break;
        case "deadline":
            command = new DeadlineCommand(tasks, input);
            break;
        case "event":
            command = new EventCommand(tasks, input);
            break;
        case "find":
            command = new FindCommand(tasks, input);
            break;
        default:
            throw new YodaException("What is the meaning of this...?");
        }
        assert command != null : "Command should not be null";
        return command;
    }
}
