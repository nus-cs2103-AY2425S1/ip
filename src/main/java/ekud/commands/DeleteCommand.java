package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.task.Task;
import ekud.ui.Ui;

/**
 * Represents the {@link Command} to delete a {@link Task} at some index in a {@link TaskList}.
 *
 * @author uniqly
 */
public class DeleteCommand extends Command {
    /** The index to delete */
    private final int index;

    /**
     * Creates a {@link DeleteCommand}.
     *
     * @param index The index to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);

        String responseDeleteCompleted = "Great work on completing your task!";
        String responseDeleteIncomplete = "I'm going to assume that task wasn't meant to be there...";
        String responseMessageFormat = """
                %s
                Proceeding with task removal directive...
                  deleted: %s
                %s""";

        Task removed = tasks.removeTask(index);

        String completeResponse = removed.isDone()
                ? responseDeleteCompleted
                : responseDeleteIncomplete;
        String listStatus = getListStatus(tasks);

        ui.addFormattedToBuffer(responseMessageFormat, completeResponse, removed, listStatus);

        storage.deleteTask(removed, ui);
    }

    /**
     * Checks the given {@link TaskList} and returns a status {@link String}.
     * @param tasks The {@link TaskList} to check.
     * @return The status {@link String}.
     */
    private static String getListStatus(TaskList tasks) {
        String responseEmpty = "Well, looks like there is nothing left for you do!";
        String responseAllCompleteFormat =
                "I've ran the numbers, and it says that all %d tasks are complete!";
        String responseElseFormat =
                "Now get a move on, you have %d out of %d incomplete tasks remaining!";

        if (tasks.isEmpty()) {
            return responseEmpty;
        } else if (tasks.isAllComplete()) {
            return String.format(responseAllCompleteFormat, tasks.getCount());
        } else {
            return String.format(responseElseFormat, tasks.getIncompleteCount(), tasks.getCount());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
