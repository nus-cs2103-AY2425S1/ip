package Commands;

import Main.TaskList;
import UI.Ui;
import Storage.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("Goodbye!");
    }
}
