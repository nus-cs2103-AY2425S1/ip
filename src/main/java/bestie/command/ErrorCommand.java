package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

public class ErrorCommand extends Command{

    private String errorMessage;
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showErrorMessage(this.errorMessage);
    }

}
