package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;

public class LeaveCommand extends Command {

    public LeaveCommand() {

    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printToInterface("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }
}
