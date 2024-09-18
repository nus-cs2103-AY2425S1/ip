package cloud.command;

import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

public abstract class Command {
    /**
     * Executes a command requested by the user, and returns the response to be displayed.
     *
     * @param tasks   a tasklist
     * @param ui      Text-based UI for displaying output
     * @param storage Storage object for accessing the save file
     * @return
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
