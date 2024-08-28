package ollie.command;

import ollie.*;
import ollie.exception.*;
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
