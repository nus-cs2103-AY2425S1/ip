package gray.command;

import gray.GrayException;
import gray.Ui;
import gray.TaskList;

public class ByeCommand extends Command {
    /**
     * Executes the bye command.
     *
     * @param ui
     * @param tasks
     * @throws GrayException
     */
    @Override
    public void execute(Ui ui, TaskList tasks) throws GrayException {
        ui.say("Bye. Hope to see you again soon!");
    }

    /**
     * Return true.
     *
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
