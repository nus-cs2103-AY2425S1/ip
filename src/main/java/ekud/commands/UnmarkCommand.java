package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.task.Task;
import ekud.ui.Ui;

/**
 * Represents the {@link Command} to mark a {@link Task} at some index in a {@link TaskList} as incomplete.
 *
 * @author uniqly
 */
public class UnmarkCommand extends Command {
    /** The index to mark as incomplete */
    private final int index;

    /**
     * Creates a {@link UnmarkCommand}.
     *
     * @param index The index to mark as incomplete.
     */
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        String responseMessageFormat = """
                Oh ho ho, did you perhaps forget something?
                It's OK, I already noted down your incompetence...
                  %s
                Tsk Tsk... Back to %d out of %d incomplete tasks you go!""";

        // find task and unmark
        Task task = tasks.getTask(index);
        String previousSaveState = task.getSaveTaskString();
        task.markAsUndone();

        // send response
        ui.addFormattedToBuffer(responseMessageFormat, task.toString(),
                tasks.getIncompleteCount(), tasks.getCount());

        // update data file
        storage.updateTaskState(task, previousSaveState, ui);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
