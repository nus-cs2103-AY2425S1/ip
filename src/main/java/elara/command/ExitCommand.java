package elara.command;

import elara.storage.Storage;
import elara.task.TaskList;
import elara.ui.Ui;

/**
 * Represents a command that exits the program that can be executed in the Elara chatbot.
 */
public class ExitCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExitMessage();
        ui.closeScanner();
    }
}
