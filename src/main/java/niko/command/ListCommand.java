package niko.command;

import niko.common.NikoException;
import niko.main.Storage;
import niko.main.Ui;
import niko.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list to the user.
     *
     * @param tasks   The task list to display.
     * @param ui      The UI to display the task list.
     * @param storage The storage (not used in this command).
     * @return
     * @throws NikoException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NikoException {
        String response = ui.showTaskList(tasks.getTasks());
        return response;
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return {@code false}, as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
