package justbot.command;

import justbot.storage.Storage;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents an unknown or unrecognized command in the Justbot application.
 * The UnknownCommand is used when the user's input does not match any known command.
 */
public class UnknownCommand extends Command {

    /**
     * Executes the command by displaying an error message to the user.
     * This method informs the user that the command they entered is invalid or unrecognized.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param ui The UI instance used to display the error message to the user.
     * @param storage The Storage instance (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.invalidCommandMessage();
    }

    /**
     * Returns the task associated with this command.
     * Since UnknownCommand does not operate on any specific task, this method returns {@code null}.
     *
     * @return {@code null}, as there is no task associated with this command.
     */
    @Override
    public Task getTask() {
        return null;
    }
}
