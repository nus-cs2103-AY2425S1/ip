package colress.command;

import colress.TaskList;
import colress.Ui;

public final class ExitCommand extends Command {
    public ExitCommand() {
        super("");
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.exit();
    }
}
