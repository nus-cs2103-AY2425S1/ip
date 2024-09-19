package elsa.command;

import elsa.task.TaskList;
import elsa.ui.Ui;

/**
 * Represents the command that finds tasks in the taskList.
 *
 * @author Aaron
 */
public class FindCommand extends Command {
    private final String taskToFind;

    /**
     * Constructs an elsa.command.FindCommand with the specified taskToFind.
     *
     * @param taskToFind The edited userInput that indicates which task(s) to search for in the taskList.
     */
    public FindCommand(String taskToFind) {
        this.taskToFind = taskToFind;
    }

    /**
     * Executes the elsa.command.FindCommand by searching through the taskList.
     *
     * @param tasks The task list, which remains unchanged by this command.
     * @param ui The elsa.ui.Ui instance that will display the tasks that match the userInput.
     * @return A response string representing the result of the command execution, which can be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.findTasks(taskToFind);
    }
}
