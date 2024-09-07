package struggling.command;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

/**
 * MarkCommand specifies instruction to mark a task as done.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Mark a Task object in TaskList as Done.
     *
     * @param index Index of the Task in TaskList.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMarkTask(tasks.markTask(this.index));
    }
}
