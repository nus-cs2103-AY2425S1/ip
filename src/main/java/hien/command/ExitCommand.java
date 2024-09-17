package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;

public class ExitCommand extends Command {
    public ExitCommand(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        ui.showGoodbye();

    }

}
