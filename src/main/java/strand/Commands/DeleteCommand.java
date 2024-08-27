package strand.Commands;

import strand.Exceptions.StrandException;
import strand.Storage;
import strand.TaskList;
import strand.Tasks.Task;
import strand.Ui;

public class DeleteCommand extends Command {
    private final Integer index;

    public DeleteCommand(Integer index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StrandException {
        Task deleted = tasks.deleteTask(this.index);
        ui.deleteTask(deleted, tasks.getNumOfTasks());
        storage.save(tasks.toFile());
    }
}
