package justbot.command;

import justbot.exception.JustbotException;
import justbot.storage.Storage;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents a command to mark a task as done in the Justbot application.
 * The MarkCommand marks a specific task in the task list as completed.
 */
public class MarkCommand extends Command {
    private int markNumber;

    /**
     * Constructs a MarkCommand with the specified task number to mark as done.
     *
     * @param markNumber The 1-based index of the task to be marked as done.
     */
    public MarkCommand(int markNumber) {
        this.markNumber = markNumber;
    }

    /**
     * Executes the command to mark the task as done in the task list.
     * The task is validated, marked as done, a confirmation message is displayed,
     * and the updated task list is saved to storage.
     * If the task number is invalid, an error message is displayed to the user.
     *
     * @param taskList The list of tasks in which the task is to be marked as done.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The Storage instance used to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateMarkTaskNumber(this.markNumber);
            Task currTask = taskList.get(this.markNumber - 1);
            currTask.setIsDone(true);
            storage.saveTasks(taskList);
            return ui.markMessage(taskList, markNumber);
        } catch (JustbotException e) {
            return ui.getJustBotExceptionMessage(e);
        }
    }

    /**
     * Returns the task associated with this command.
     * Since MarkCommand does not operate on any specific task directly, this method returns {@code null}.
     *
     * @return {@code null}, as there is no task directly associated with this command.
     */
    @Override
    public Task getTask() {
        return null;
    }
}
