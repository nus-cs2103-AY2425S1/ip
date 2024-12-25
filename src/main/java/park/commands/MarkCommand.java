package park.commands;

import park.exceptions.ParkException;
import park.storage.Storage;
import park.storage.TaskList;
import park.task.Task;
import park.ui.Ui;

/**
 * Represents a command that marks an existing task as done.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a MarkCommand object.
     *
     * @param index Index of task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        try {
            Task t = tasks.get(index);
            String oldLine = t.encode();
            t.mark();
            assert t.getStatusIcon().equals("[X]") : "Task should be marked";
            String newLine = t.encode();
            storage.modify(oldLine, newLine);
            ui.setMarkResponse(t.toString());
        } catch (IndexOutOfBoundsException e) {
            throw ParkException.invalidIndexException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
