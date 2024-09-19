package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.task.Task;
import ekud.ui.Ui;

/**
 * Represents the {@link Command} to change the {@link Task.Priority} of the {@link Task} at a
 * selected index.
 */
public class SetPriorityCommand extends Command {
    public static final String PRIORITY_TOKEN = "/priority";
    private final Task.Priority priority;
    private final int index;

    /**
     * Creates a {@link SetPriorityCommand}.
     *
     * @param index The index to set priority.
     * @param type The type of priority to give to the task at the index.
     * @throws EkudException When an invalid priority type is supplied.
     */
    public SetPriorityCommand(int index, String type) throws EkudException {
        assert index >= 0 : "index should be non negative";

        this.index = index;
        priority = Task.Priority.getPriority(type);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);
        String responseFormat = "Thank me later! I've changed the priority of your task:\n  %s";

        // find task and change priority
        Task task = tasks.getTask(index);
        String previousSaveState = task.getSaveTaskString();

        // set priority and output response
        task.setPriority(priority);
        ui.addFormattedToBuffer(responseFormat, task);

        // overwrite task in save
        storage.updateTaskState(task, previousSaveState, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
