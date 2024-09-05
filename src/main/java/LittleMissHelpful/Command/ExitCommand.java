package LittleMissHelpful.Command;

import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Task.Event;
import LittleMissHelpful.TaskList;
import LittleMissHelpful.Ui;
import LittleMissHelpful.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        ui.showExit();
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
