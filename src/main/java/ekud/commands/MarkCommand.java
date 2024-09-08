package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.task.Task;
import ekud.ui.Ui;

/**
 * Represents the {@link Command} to mark a {@link Task} at some index in a {@link TaskList} as complete.
 *
 * @author uniqly
 */
public class MarkCommand extends Command {
    /** The index to mark as complete */
    private final int index;

    /**
     * Creates a {@link MarkCommand}.
     * @param index The index to mark as complete.
     */
    public MarkCommand(int index) {
        assert index >= 0 : "index should be non negative";

        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);

        Task task = tasks.getTask(index);
        String previousSaveState = task.getSaveTaskString();
        task.markAsDone();
        String listStatus = tasks.isAllComplete()
                ? String.format("WOOHOO!! YOU DID IT! Everything is complete!! All %d of them",
                tasks.getCount())
                : String.format("Woohoo!! Only %d out of %d tasks more to go",
                tasks.getIncompleteCount(),
                tasks.getCount());
        String message = String.format("""
                        Wowie!! You've completed your task!
                        I shall mark it as complete in celebration!
                          %s
                        %s!""",
                task,
                listStatus);
        ui.addToBuffer(message);

        // update data file
        storage.updateTaskState(task, previousSaveState, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
