package mittens.commands;

import mittens.MittensException;
import mittens.storage.Storage;
import mittens.task.TaskList;
import mittens.ui.Ui;

/**
 * Represents a command for exiting the program.
 */
public class ExitCommand extends Command {
    
    /**
     * Creates a new ExitCommand object.
     */
    public ExitCommand() {
        super();
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);

            ui.showGoodbyeMessage();
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
