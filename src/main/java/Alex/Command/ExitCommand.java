package Alex.Command;

import Alex.Storage.Storage;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends CommandBase {
    /**
     * Constructs an ExitCommand, setting the isExit flag to true.
     */
    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        System.exit(0);
    }
}

