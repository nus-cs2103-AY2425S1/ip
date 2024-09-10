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
        assert tl != null : "Task list must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert details != null : "Details must not be null";

        ui.setResponse(this.exitMsg);
        ui.printResponse();
    }
}
