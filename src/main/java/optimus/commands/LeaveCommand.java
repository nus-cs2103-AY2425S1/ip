package optimus.commands;

import optimus.Storage;
import optimus.TaskList;

/**
 * Command to stop program
 */
public class LeaveCommand extends Command {

    public LeaveCommand() {
    }

    /**
     * Prints a goodbye message to the UI
     *
     * @param storage - permanent storage
     * @param tasks   - session storage
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a flag to Optimus to end chat
     *
     * @return - Always false
     */
    @Override
    public boolean shouldContinue() {
        return false;
    }
}
