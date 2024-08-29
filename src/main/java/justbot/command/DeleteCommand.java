package justbot.command;

import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;
import justbot.storage.Storage;
import justbot.exception.JustbotException;

/**
 * Represents a command to delete a task from the task list in the Justbot application.
 * The DeleteCommand deletes a task specified by its index in the task list.
 */
public class DeleteCommand extends Command {
    private int deleteNumber;

    /**
     * Constructs a DeleteCommand with the specified task number to delete.
     *
     * @param deleteNumber The 1-based index of the task to be deleted from the task list.
     */
    public DeleteCommand(int deleteNumber) {
        this.deleteNumber = deleteNumber;
    }

    /**
     * Executes the command to delete the task from the task list.
     * The task is validated, deleted from the task list, a confirmation message is displayed, and the updated task list is saved to storage.
     * If the task number is invalid, an error message is displayed to the user.
     *
     * @param taskList The list of tasks from which the task is to be deleted.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The Storage instance used to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateDeleteTaskNumber(this.deleteNumber);
            ui.deleteTaskMessage(taskList, this.deleteNumber);
            taskList.delete(this.deleteNumber);
            storage.saveTasks(taskList);
        } catch (JustbotException e) {
            ui.getJustBotExceptionMessage(e);
        }
    }

    /**
     * Returns the task associated with this command.
     * Since DeleteCommand does not operate on any specific task directly, this method returns {@code null}.
     *
     * @return {@code null}, as there is no task directly associated with this command.
     */
    @Override
    public Task getTask() {
        return null;
    }
}
