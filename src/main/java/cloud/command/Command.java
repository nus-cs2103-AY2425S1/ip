package cloud.command;

import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

public abstract class Command {
    /**
     * Executes a command requested by the user, and returns the response to be displayed.
     *
     * @param tasks   a TaskList
     * @param ui      UI for retrieving output to be displayed
     * @param storage Storage object for accessing the save file
     * @return The response String to be displayed by the application
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
