package park.commands;

import park.exceptions.ParkException;
import park.storage.Storage;
import park.storage.TaskList;
import park.task.Task;
import park.ui.Ui;

/**
 * Represents a command that adds a task to the list.
 */
public class AddCommand extends Command {

    private final Task t;

    /**
     * Constructs an AddCommand object.
     *
     * @param t Task to be added.
     */
    public AddCommand(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        tasks.add(t);
        storage.append(t);
        ui.setAddResponse(t.toString(), tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
