package ollie.command;

import ollie.*;
import ollie.exception.OllieException;
import ollie.task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        Task task = tasks.delete(index);
        ui.showRemoveTask(task, tasks.getSize());
    }
}

