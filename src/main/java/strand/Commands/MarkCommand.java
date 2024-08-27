package strand.Commands;

import strand.Exceptions.StrandException;
import strand.Storage;
import strand.TaskList;
import strand.Tasks.Task;
import strand.Ui;

public class MarkCommand extends Command {
    private final Integer index;
    private final Boolean mark;
    public MarkCommand(Integer index, Boolean mark) {
        this.index = index;
        this.mark = mark;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StrandException {
        Task task = tasks.mark(this.index, this.mark);
        ui.markTask(task, this.mark);
        storage.save(tasks.toFile());
    }
}
