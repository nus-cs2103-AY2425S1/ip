package elsa.command;

import elsa.task.TaskList;
import elsa.ui.Ui;

/**
 * Represents the command that lists the tasks in the taskList.
 *
 * @author Aaron
 */
public class ListCommand extends Command {
    /**
     * Executes the elsa.command.ListCommand by listing all the tasks in the taskList.
     *
     * @param tasks The task list, which remains unchanged by this command.
     * @param ui The elsa.ui.Ui instance that will display the list of tasks.
     * @return A response string representing the result of the command execution, which can be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.listTasks();
    }
}
