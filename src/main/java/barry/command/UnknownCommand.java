package barry.command;

import barry.util.TaskList;
import barry.util.Ui;
import barry.util.Storage;

/**
 * Represents a command that handles unknown commands.
 */
public class UnknownCommand extends Command {
    private String msg;

    /**
     * Constructs an UnknownCommand with the specified error message.
     *
     * @param msg The error message to be displayed.
     */
    public UnknownCommand(String msg) {
        this.msg = msg;
    }

    /**
     * Executes the unknown command.
     *
     * @param allTasks The list of all tasks.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system.
     */
    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) {
        ui.showError(msg);
    }
}
