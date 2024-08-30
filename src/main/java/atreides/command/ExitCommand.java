package atreides.command;

import atreides.task.TaskList;
import atreides.ui.Storage;
import atreides.ui.Ui;

public class ExitCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    @Override
    public boolean isExit() {
        return true;
    }
}
