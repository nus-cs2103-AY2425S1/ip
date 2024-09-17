package monique.command;

import monique.exception.MoniqueException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

/**
 * Represents an abstract command in the application.
 * Commands define operations that can be executed, and each command must implement
 * how it interacts with the task list, user interface, and storage.
 */
public abstract class Command {
    private String commandType;
    public Command(String commandType) {
        this.commandType = commandType;
    }
    /**
     * Executes the command with the given task list, user interface, and storage.
     * Subclasses must implement this method to define specific command behavior.
     *
     * @param tasks the <code>TaskList</code> to interact with during execution
     * @param ui the <code>Ui</code> instance used for interacting with the user
     * @param storage the <code>Storage</code> instance used for data persistence
     * @throws MoniqueException if an error occurs during command execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MoniqueException;

    /**
     * Returns whether this chatbot will be active after the command executes.
     * @return true if the bot remains active after the command, false otherwise
     */
    public abstract boolean isActive();

    /**
     * Returns a string response after the command is executed.
     *
     * @return the response string
     */
    public abstract String getResponse(Ui ui);

    /**
     * Returns a string of the type of command.
     *
     * @return the command-type string
     */
    public String getCommandType() {
        return commandType;
    }
}
