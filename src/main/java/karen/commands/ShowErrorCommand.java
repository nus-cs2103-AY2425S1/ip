package karen.commands;

import karen.tasks.TaskList;
import karen.util.Ui;

/**
 * Class to represent an error message to be passed to JavaFx
 */
public class ShowErrorCommand extends Command {
    private String errorMessage;

    public ShowErrorCommand(String msg) {
        this.errorMessage = msg;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return this.errorMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
