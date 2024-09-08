package justbot.command;

import justbot.storage.Storage;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents a command to exit the Justbot application.
 * The ByeCommand displays a farewell message and signals the application to terminate.
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand, which displays a farewell message to the user.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param ui The UI instance used to display messages to the user.
     * @param storage The Storage instance (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.byeMessage();
    }

    /**
     * Indicates that this command should terminate the application.
     *
     * @return {@code true}, indicating that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the task associated with this command.
     * Since ByeCommand does not operate on any task, this method returns {@code null}.
     *
     * @return {@code null}, as there is no task associated with this command.
     */
    @Override
    public Task getTask() {
        return null;
    }
}
