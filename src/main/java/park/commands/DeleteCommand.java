package park.commands;

import park.exceptions.ParkException;
import park.storage.Storage;
import park.storage.TaskList;
import park.task.Task;
import park.ui.Ui;

/**
 * Represents a command that deletes a task from the list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        try {
            Task t = tasks.get(index);
            storage.delete(t);
            ui.setResponse("Noted. I've removed this task:" + t + "\n");
            tasks.delete(index);
            ui.setResponse("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new ParkException("invalid index");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
