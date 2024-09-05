package niko.command;

import niko.main.Storage;
import niko.main.Ui;
import niko.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, showing a goodbye message to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI to display the goodbye message.
     * @param storage The storage (not used in this command).
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = ui.showGoodbyeMessage();
        return response;
    }

    /**
     * Indicates that this command will exit the application.
     *
     * @return {@code true}, as this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
