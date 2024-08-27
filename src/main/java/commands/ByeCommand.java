package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exit();
    }


    @Override
    public boolean isExit() {
        return true;
    }
}
