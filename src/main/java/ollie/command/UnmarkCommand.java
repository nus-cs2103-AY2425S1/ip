package ollie.command;

import ollie.*;
import ollie.exception.OllieException;
import ollie.task.Task;

public class UnmarkCommand extends Command{
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        Task task = tasks.markAsUndone(index);
        ui.showMarkAsUndone(task);
    }
}
