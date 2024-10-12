package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, which returns a message from Ui.
     *
     * @param list the task list
     * @param ui the user interface to interact with the user
     * @param storage the storage
     * @return a string representing the exit message
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.exitMessage();
    }
}
