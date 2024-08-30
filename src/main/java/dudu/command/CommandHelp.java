package dudu.command;

import dudu.command.Command;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

import java.io.IOException;

public class CommandHelp extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        ui.helpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
