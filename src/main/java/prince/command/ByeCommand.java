package prince.command;

import prince.tasks.TaskList;
import prince.util.Storage;
import prince.util.Ui;

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
