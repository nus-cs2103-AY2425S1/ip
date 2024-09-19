package struggling.command;

import java.io.IOException;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

/**
 * HighPriorityCommand specifies instructions for setting a
 * task to high priority.
 */
public class HighPriorityCommand extends Command {

    private final int index;

    /**
     * Mark a Task object in TaskList as Done.
     *
     * @param index Index of the Task in TaskList.
     */
    public HighPriorityCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showSetTaskPriorityHigh(tasks.setTaskPriorityHigh(this.index));
        storage.save(tasks.getTasksState());
    }
}
