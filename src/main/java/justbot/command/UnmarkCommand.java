package justbot.command;

import justbot.exception.JustbotException;
import justbot.storage.Storage;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the Justbot application.
 * The UnmarkCommand marks a specific task in the task list as not completed.
 */
public class UnmarkCommand extends Command {
    private int unmarkNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number to unmark as not done.
     *
     * @param unmarkNumber The 1-based index of the task to be unmarked as not done.
     */
    public UnmarkCommand(int unmarkNumber) {
        this.unmarkNumber = unmarkNumber;
    }

    /**
     * Executes the command to unmark the task as not done in the task list.
     * The task is validated, unmarked as not done, a confirmation message is displayed,
     * and the updated task list is saved to storage.
     * If the task number is invalid, an error message is displayed to the user.
     *
     * @param taskList The list of tasks in which the task is to be unmarked as not done.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The Storage instance used to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateUnmarkTaskNumber(unmarkNumber);
            Task currTask = taskList.get(this.unmarkNumber - 1);
            currTask.setIsDone(false);
            storage.saveTasks(taskList);
            return ui.unmarkMessage(taskList, unmarkNumber);
        } catch (JustbotException e) {
            return ui.getJustBotExceptionMessage(e);
        }
    }

    /**
     * Returns the task associated with this command.
     * Since UnmarkCommand does not operate on any specific task directly, this method returns {@code null}.
     *
     * @return {@code null}, as there is no task directly associated with this command.
     */
    @Override
    public Task getTask() {
        return null;
    }
}
