package gray.command;

import gray.GrayException;
import gray.Ui;
import gray.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks) throws GrayException {
        ui.say("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
