package command;

import exception.InvalidCommandException;
import exception.LevelHundredException;
import task.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The UserCommand interface deals with executing user commands
 */
public abstract class UserCommand {

    /**
     * Creates the appropriate UserCommand based on the command name
     *
     * @param commandName String representing the name of the user command
     * @return UserCommand to be executed
     */
    public static UserCommand toCommand(String commandName) throws InvalidCommandException {
        switch (commandName) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            return new UpdateStatusCommand();
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand();
        case "delete":
            return new DeleteCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Checks whether to continue running the chatbot
     *
     * @return Boolean of whether the command is an exit command
     */
    public boolean continueRunning() {
        return true;
    }

    /**
     * Execute command that user inputs
     *
     * @param input String representing the line that user inputs
     */
    public abstract void execute(String input, Ui ui, Storage storage, TaskList taskList) throws LevelHundredException;
}