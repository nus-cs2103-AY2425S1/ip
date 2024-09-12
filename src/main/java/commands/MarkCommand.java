package commands;

import static util.Utility.INDENT;
import static util.Utility.NEW_LINE;

import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;

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

        String[] indexes = details[1].split("\\s+");
        int[] toBeUpdatedIndexes = new int[indexes.length];

        for (int i = 0; i < indexes.length; i++) {
            int idx = Integer.parseInt(indexes[i]);
            if (!tl.isValidIdx(idx)) {
                ui.setResponse(
                        String.format("Someones tryna be funny, idx: %d is out of range!", idx));
                ui.printResponse();
                return;
            }
            toBeUpdatedIndexes[i] = idx;
        }

        StringBuilder updatedTasks = new StringBuilder();
        for (int i : toBeUpdatedIndexes) {
            Task t = (this.command == CommandTypes.MARK) ? tl.markAsDone(i, storage)
                    : tl.markAsUndone(i, storage);
            if (t != null) {
                updatedTasks.append(INDENT + t.toString() + NEW_LINE);
            }
        }

        String msg = (this.command == CommandTypes.MARK) ? "Nice! I've marked this task as done:"
                : "Ok, I've marked this task as not done yet:";
        ui.setResponse(msg, updatedTasks.toString());
        ui.printResponse();
    }
}
