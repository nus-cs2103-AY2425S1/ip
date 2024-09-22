package streams.command;


import streams.exception.StreamsException;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command that can be executed on the task list.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list to perform the command on.
     * @param ui The user interface to display messages.
     * @param storage The storage to save any changes.
     * @throws StreamsException If there's an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException;

    /**
     * Checks if this command is an exit command.
     *
     * @return true if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
