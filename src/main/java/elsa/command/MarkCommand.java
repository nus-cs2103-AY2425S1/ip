package elsa.command;

import elsa.task.TaskList;
import elsa.ui.Ui;

/**
 * Represents the command that marks tasks in the taskList.
 *
 * @author Aaron
 */
public class MarkCommand extends Command {
    /**
     * The index of the task to be marked as completed.
     */
    private int taskIndex;

    /**
     * Constructs a elsa.command.MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to mark as completed.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark the task at the specified index as completed.
     *
     * @param tasks The task list to update.
     * @param ui The elsa.ui.Ui instance, which is not used in this command.
     * @return A response string representing the result of the command execution, which can be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.markTask(taskIndex);
    }
}
