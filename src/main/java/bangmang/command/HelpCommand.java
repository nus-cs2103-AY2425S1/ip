package LittleMissHelpful.Command;

import LittleMissHelpful.Storage.Storage;
import LittleMissHelpful.Tasks.TaskList;
import LittleMissHelpful.Ui.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        return ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
