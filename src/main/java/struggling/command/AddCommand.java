package struggling.command;

import java.io.IOException;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;
import struggling.task.Task;

/**
 * AddCommand specifies the tasks to be added.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Adds a new Task object in TaskList.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showAddTask(tasks.addTask(this.task), tasks.getSize());
        storage.save(tasks.getTasksState());
    }
}
