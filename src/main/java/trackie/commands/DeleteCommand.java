package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.deleteTask(arguments);
            storage.save();
        } catch (TrackieException e) {
            ui.displayErrorMessage(e);
        }
    }
}
