package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.markTask(arguments);
        } catch (TrackieException e) {
            ui.displayErrorMessage(e);
        }
    }
}
