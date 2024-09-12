package atreides.command;

import atreides.task.TaskList;
import atreides.ui.Storage;
import atreides.ui.Ui;

public class ExitCommand implements Command {

    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }

    /**
     * {@inheritDoc}
     * @return true as this command exits the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
