package Karen.commands;

import Karen.tasks.TaskList;
import Karen.util.Ui;

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
