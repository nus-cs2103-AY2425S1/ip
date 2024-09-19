package struggling.command;

import java.io.IOException;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

/**
 * LowPriorityCommand specifies instructions for setting a
 * task to low priority.
 */
public class LowPriorityCommand extends Command {
    private final int index;

    /**
     * Mark a Task object in TaskList as Done.
     *
     * @param index Index of the Task in TaskList.
     */
    public LowPriorityCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showSetTaskPriorityLow(tasks.setTaskPriorityLow(this.index));
        storage.save(tasks.getTasksState());
    }
}
