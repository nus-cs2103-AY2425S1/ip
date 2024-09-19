package elsa.command;

import elsa.task.TaskList;
import elsa.ui.Ui;

/**
 * Represents the command that unmarks tasks in the taskList.
 *
 * @author Aaron
 */
public class UnmarkCommand extends Command {
    /**
     * The index of the task to be unmarked.
     */
    private int taskIndex;

    /**
     * Constructs a elsa.command.UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to unmark the task at the specified index.
     *
     * @param tasks The task list to update.
     * @param ui The elsa.ui.Ui instance, which is not used in this command.
     * @return A response string representing the result of the command execution, which can be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.unmarkTask(taskIndex);
    }
}
