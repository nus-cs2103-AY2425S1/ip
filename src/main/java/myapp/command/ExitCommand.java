package myapp.command;

import myapp.core.BingBongUi;
import myapp.core.Storage;
import myapp.task.TaskList;


public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, BingBongUi ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
