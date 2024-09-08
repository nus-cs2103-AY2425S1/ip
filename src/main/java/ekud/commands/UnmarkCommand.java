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
        assert index >= 0 : "index should be non negative";

        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);

        Task task = tasks.getTask(index);
        String previousSaveState = task.getSaveTaskString();
        task.markAsUndone();
        String message = String.format("""
                        Oh ho ho, did you perhaps forget something?
                        It's OK, I already noted down your incompetence...
                          %s
                        Tsk Tsk... Back to %d out of %d incomplete tasks you go!""",
                tasks.getTask(index),
                tasks.getIncompleteCount(),
                tasks.getCount());
        ui.addToBuffer(message);

        // update data file
        storage.updateTaskState(task, previousSaveState, ui);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
