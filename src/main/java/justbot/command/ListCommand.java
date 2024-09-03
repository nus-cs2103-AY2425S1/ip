package justbot.command;

import justbot.exception.JustbotException;
import justbot.storage.Storage;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents a command to list all tasks in the task list in the Justbot application.
 * The ListCommand displays all the tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks in the task list.
     * If the task list is not empty, the tasks are displayed to the user.
     * If the task list is empty, an error message is displayed.
     *
     * @param taskList The list of tasks to be displayed.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The Storage instance (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateNotEmpty();
            return ui.listMessage(taskList);
        } catch (JustbotException e) {
            return ui.getJustBotExceptionMessage(e);
        }
    }

    /**
     * Returns the task associated with this command.
     * Since ListCommand does not operate on any specific task directly, this method returns {@code null}.
     *
     * @return {@code null}, as there is no task directly associated with this command.
     */
    @Override
    public Task getTask() {
        return null;
    }
}
