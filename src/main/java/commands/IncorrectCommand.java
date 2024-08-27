package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

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
