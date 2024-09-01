package karen.commands;

import karen.tasks.TaskList;
import karen.util.Ui;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showUnknownInputError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
