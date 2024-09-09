package charlotte.command;

import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

/**
 * The Command class serves as an abstract class for all commands that can be executed
 * within the Charlotte chatbot.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Constructs a Command instance. By default, a command is not an exit command.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the command. This method is abstract and must be implemented by any concrete subclass of Command.
     *
     * @param tasks   The TaskList object containing the current list of tasks.
     * @param ui      The Ui object responsible for interacting with the user.
     * @param storage The Storage object responsible for saving and loading tasks.
     * @throws CharlotteException If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException;

    /**
     * Returns whether this command is an exit command.
     *
     * @return true if this command is an exit command; false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
