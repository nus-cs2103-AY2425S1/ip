package commands;

import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;
import util.Utility;

/**
 * Concrete implementation of delete command class.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details) {
        assert tl != null : "Task list must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert details != null : "Details must not be null";

        try {
            int idx = Integer.parseInt(details[1]);
            if (!tl.isValidIdx(idx)) {
                ui.setResponse(
                        String.format("Someones tryna be funny, idx: %d is out of range!", idx));
                return;
            }
            Task t = tl.deleteTask(idx, storage);
            ui.setResponse("Ok! I've removed this task:", Utility.INDENT + t.toString(),
                    String.format("You now have %d tasks in your list.", tl.size()));
        } catch (NumberFormatException e) {
            ui.setResponse("Invalid index: " + details[1] + " is not a number!");
        } finally {
            ui.printResponse();
        }
    }
}
