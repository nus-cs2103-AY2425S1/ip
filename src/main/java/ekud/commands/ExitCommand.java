package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.components.Ui;

/**
 * Represents a {@link Command} to terminate EKuD.
 *
 * @author uniqly
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
