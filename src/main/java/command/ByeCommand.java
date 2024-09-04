package command;

import tasks.TaskList;
import util.Storage;
import util.Ui;

/**
 * Command that terminates the chatbot.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.toggleIsExit();
        ui.showBye();
    }
}
