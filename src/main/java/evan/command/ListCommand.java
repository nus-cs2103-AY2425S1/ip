package evan.command;

import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;

/**
 * Represents a command that the chatbot can execute to list out the currently stored tasks.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.display();
        ui.showLine();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
