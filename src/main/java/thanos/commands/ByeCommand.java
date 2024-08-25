package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        ui.display("Bye. Hope to see you again soon!\n");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
