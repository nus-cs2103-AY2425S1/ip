package main.LittleMissHelpful.Command;

import main.LittleMissHelpful.Exception.InvalidCommandException;
import main.LittleMissHelpful.TaskList;
import main.LittleMissHelpful.Ui;
import main.LittleMissHelpful.Storage;

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
