package Commands;

import Main.TaskList;
import UI.Ui;
import Storage.Storage;

public class InvalidCommand extends Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError(errorMessage);
    }
}
