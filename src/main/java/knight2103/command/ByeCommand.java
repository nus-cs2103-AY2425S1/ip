package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.Ui;
import knight2103.files.Storage;

public class ByeCommand extends Command {
    ByeCommand() {
        super(CommandVerb.BYE);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    };

    public boolean isExit() {
        return true;
    }
}
