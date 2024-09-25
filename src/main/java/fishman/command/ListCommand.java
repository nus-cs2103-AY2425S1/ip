package fishman.command;

import fishman.task.TaskList;
import fishman.utils.Ui;

/**
 * Represents the command to display the task list.
 * This command implements the Command interface and is for
 * return the message containing the tasks currently in the task list.
 */
public class ListCommand implements Command {
    /**
     * @inheritDoc
     *
     *      Displays the tasks in the task list.
     *
     * @param taskList The TaskList which the tasks will be retrieved from.
     * @param ui The Ui object used to display the tasks in the task list.
     * @return The confirmation message containing the tasks currently in the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.getTaskListMessage(taskList);
    }
}
