package ollie.command;

import ollie.exception.OllieException;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        // Save data
        storage.save(tasks.getTasks());
        ui.showExit();
    };
}
