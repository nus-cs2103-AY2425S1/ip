package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;

public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.endConversation();
    }
}
