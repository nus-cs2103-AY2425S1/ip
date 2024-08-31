package park.commands;

import park.exceptions.ParkException;
import park.storage.Storage;
import park.storage.TaskList;
import park.task.Task;
import park.ui.Ui;

/**
 * Represents a command that marks an existing task as not done.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs a UnmarkCommand object.
     *
     * @param i Index of task to be marked as not done.
     */
    public UnmarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        try {
            Task t = tasks.get(index);
            String oldLine = t.encode();
            t.unmark();
            String newLine = t.encode();
            storage.modify(oldLine, newLine);
            ui.showToUser("OK, I've marked this task as not done yet:" + t);
        } catch (IndexOutOfBoundsException e) {
            throw new ParkException("invalid index");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
