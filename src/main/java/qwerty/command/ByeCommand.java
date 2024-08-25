package qwerty.command;

import java.util.HashMap;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.Ui;

public class ByeCommand extends Command {

    public ByeCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

}
