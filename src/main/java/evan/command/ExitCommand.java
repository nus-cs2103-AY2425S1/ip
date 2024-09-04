package evan.command;

import evan.service.Storage;
import evan.service.TaskList;
import evan.service.Ui;

/**
 * Represents a command that the chatbot can execute to exit the session.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
