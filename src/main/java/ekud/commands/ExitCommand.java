package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.ui.Ui;

/**
 * Represents a {@link Command} to terminate EKuD.
 *
 * @author uniqly
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);
        // does nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
