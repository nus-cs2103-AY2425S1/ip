package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.task.Task;
import ekud.ui.Ui;

/**
 * Represents a {@link Command} to add a task to some {@link TaskList}.
 *
 * @author uniqly
 */
public class AddCommand extends Command {
    /** The task to be added */
    private final Task task;

    /**
     * Creates the {@link AddCommand} with the given task.
     * @param task The {@link Task} to add.
     */
    public AddCommand(Task task) {
        assert task != null : "task should not be null";

        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);
        String responseFormat = "added: %s\nAnd another one; %d out of %d tasks to complete...";

        tasks.addTask(task);

        ui.addFormattedToBuffer(responseFormat, task.toString(),
                tasks.getIncompleteCount(), tasks.getCount());

        storage.saveNewTask(task, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
