package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.Ui;
import knight2103.files.Storage;

public class ListCommand extends Command {
    ListCommand() {
        super(CommandVerb.LIST);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    };

    public boolean isExit() {
        return false;
    }
}
