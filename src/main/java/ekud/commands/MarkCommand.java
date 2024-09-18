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
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);
        String responseMessageFormat = """
                Wowie!! You've completed your task!
                I shall mark it as complete in celebration!
                  %s
                %s!""";

        // find task and mark as done
        Task task = tasks.getTask(index);
        String previousSaveState = task.getSaveTaskString();
        task.markAsDone();

        // send response
        String listStatus = getListStatus(tasks);
        ui.addFormattedToBuffer(responseMessageFormat, task.toString(), listStatus);

        // update data file
        storage.updateTaskState(task, previousSaveState, ui);
    }

    private static String getListStatus(TaskList tasks) {
        String responseAllCompleteFormat = "WOOHOO!! YOU DID IT! Everything is complete!! All %d of them";
        String responseInProgressFormat = "Woohoo!! Only %d out of %d tasks more to go";

        if (tasks.isAllComplete()) {
            return String.format(responseAllCompleteFormat, tasks.getCount());
        } else {
            return String.format(responseInProgressFormat, tasks.getIncompleteCount(), tasks.getCount());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
