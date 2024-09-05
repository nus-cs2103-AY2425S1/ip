package dumpling.command;

import dumpling.Storage;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

/**
 * ByeCommand class, inherits Command
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        executeAndReturnLog(taskList, storage);
        ui.exit();
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) {
        storage.save(taskList);
        Ui ui = new Ui();
        return ui.getExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
