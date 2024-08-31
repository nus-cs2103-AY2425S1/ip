package commands;

import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Concrete implementation of a bye command class.
 */
public class ByeCommand extends Command {
    private final String exitMsg = "Bye. Hope to see you again soon!";

    public ByeCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details) {
        ui.printResponse(this.exitMsg);
    }
}
