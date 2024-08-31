package atreides.command;

import atreides.task.TaskList;
import atreides.ui.Storage;
import atreides.ui.Ui;

public class ListCommand implements Command {

    /**
     * Ui will print the list of tasks current stored
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.showList();
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
