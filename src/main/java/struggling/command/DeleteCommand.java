package struggling.command;

import java.io.IOException;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

/**
 * AddCommand specifies the tasks to be deleted.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Deletes a specified Task object from TaskList.
     *
     * @param index Index of the Task in TaskList.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showDeleteTask(tasks.deleteTask(this.index), tasks.getSize());
        storage.save(tasks.getTasksState());
    }
}
