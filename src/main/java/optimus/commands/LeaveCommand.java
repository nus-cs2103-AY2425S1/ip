package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;

public class LeaveCommand extends Command {

    public LeaveCommand() {
    }

    /**
     * Prints a goodbye message to the UI
     * @param storage - permanent storage
     * @param tasks - session storage
     * @param ui - user interface
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printToInterface("Bye. Hope to see you again soon!");
    }

    /**
     * Returns a flag to Optimus to end chat
     * @return - Always false
     */
    @Override
    public boolean shouldContinue() {
        return false;
    }
}
