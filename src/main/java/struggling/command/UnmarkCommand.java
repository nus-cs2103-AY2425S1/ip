package struggling.command;

import java.io.IOException;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

/**
 * UnmarkCommand specifies instruction to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Mark a Task object in TaskList as not Done.
     *
     * @param index Index of the Task in TaskList.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showUnmarkTask(tasks.unmarkTask(this.index));
        storage.save(tasks.getTasksState());
    }
}
