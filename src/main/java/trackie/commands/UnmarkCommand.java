package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.unmarkTask(arguments);
        } catch (TrackieException e) {
            ui.displayErrorMessage(e);
        }
    }
}
