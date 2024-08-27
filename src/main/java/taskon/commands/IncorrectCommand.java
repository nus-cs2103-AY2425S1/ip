package taskon.commands;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

public class IncorrectCommand extends Command {

    public final String result;
    public IncorrectCommand(String result) {
        this.result = result;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError(result);
    }
}
