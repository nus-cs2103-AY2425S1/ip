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
        try {
            int idx = Integer.parseInt(details[1]);
            if (!tl.isValidIdx(idx)) {
                ui.printResponse(
                        String.format("%sSomeones tryna be funny, idx: %d is out of range!",
                                Utility.INDENT, idx));
                return;
            }
            Task t = tl.deleteTask(idx, storage);
            ui.printResponse("Ok! I've removed this task:", Utility.INDENT + t.toString(),
                    String.format("You now have %d tasks in your list.", tl.size()));
        } catch (NumberFormatException e) {
            ui.printResponse("Invalid index: " + details[1] + " is not a number!");
        }
    }
}
