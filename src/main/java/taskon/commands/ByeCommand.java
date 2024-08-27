package taskon.commands;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

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
