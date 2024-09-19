package command;

import exception.InvalidCommandException;
import task.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The UserCommand interface deals with executing user commands
 */
public abstract class UserCommand {
    private String response = "";

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
        case "find":
            return new FindCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Checks whether to continue running the program
     * @return Boolean of whether the command is an exit command
     */
    public boolean continueRunning() {
        return true;
    }

    /**
     * Executes command that user inputs
     * @param userInput String representing the user input
     * @param ui Ui to print output
     * @param storage Storage where tasks are saved
     * @param taskList Task list of current tasks
     */
    public abstract void execute(String userInput, Ui ui, Storage storage, TaskList taskList);

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}