package commands;

import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;
import util.Utility;

/**
 * Concrete implementation of mark command class.
 */
public class MarkCommand extends Command {
    public MarkCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details) {
        assert tl != null : "Task list must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert details != null : "Details must not be null";

        try {
            int index = Integer.parseInt(details[1]);
            if (!tl.isValidIdx(index)) {
                ui.setResponse(
                        String.format("Someones tryna be funny, idx: %d is out of range!", index));
                return;
            }
            if (this.command == CommandTypes.MARK) {
                Task t = tl.markAsDone(index, storage);
                if (t != null) {
                    ui.setResponse("Nice! I've marked this task as done:", Utility.INDENT + t);
                } else {
                    ui.setResponse("Task has already been completed!");
                }
            } else {
                Task t = tl.markAsUndone(index, storage);
                if (t != null) {
                    ui.setResponse("Ok, I've marked this task as not done yet:",
                            Utility.INDENT + t);
                } else {
                    ui.setResponse("Task is already unmarked!");
                }
            }
        } catch (NumberFormatException e) {
            ui.setResponse("Invalid index passed, not a number!: " + details[1]);
        } finally {
            ui.printResponse();
        }
    }
}
