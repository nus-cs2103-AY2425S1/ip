package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.exitMessage();
    }
}
