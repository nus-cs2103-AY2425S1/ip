package dumpling.command;

import dumpling.task.TaskList;
import dumpling.Ui.Ui;
import dumpling.Storage;

public class ByeCommand extends Command {

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

    public boolean isExit() {
        return true;
    }
}
