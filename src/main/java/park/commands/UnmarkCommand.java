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
     * @param index Index of task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        try {
            Task t = tasks.get(index);
            String oldLine = t.encode();
            t.unmark();
            assert t.getStatusIcon().equals("[ ]") : "Task should be unmarked";
            String newLine = t.encode();
            storage.modify(oldLine, newLine);
            ui.setResponse("OK, I've marked this task as not done yet:" + t);
        } catch (IndexOutOfBoundsException e) {
            throw new ParkException("invalid index");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
