package atreides.command;

import atreides.task.TaskList;
import atreides.ui.Storage;
import atreides.ui.Ui;


/**
 * A command that lists all tasks in the TaskList when executed.
 */
public class ListCommand implements Command {

    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) {
        return tasks.showList();
    }

    /**
     * Ui will print the list of tasks current stored
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = executeString(tasks, ui, storage);
        ui.showMessage(listOfTasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
