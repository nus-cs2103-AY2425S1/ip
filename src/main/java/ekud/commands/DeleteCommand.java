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
        assert index >= 0 : "index should be non negative";

        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);

        Task removed = tasks.removeTask(index);
        String completeResponse = removed.isDone()
                ? "Great work on completing your ekud.task!"
                : "I'm going to assume that ekud.task wasn't meant to be there...";

        String listStatus;
        if (tasks.isEmpty()) {
            listStatus = "Well, looks like there is nothing left for you do!";
        } else if (tasks.isAllComplete()) {
            listStatus = String.format("I've ran the numbers, and it says that all %d tasks are complete!",
                    tasks.getCount());
        } else {
            listStatus = String.format("Now get a move on, "
                            + "you have %d out of %d incomplete tasks remaining!",
                    tasks.getIncompleteCount(),
                    tasks.getCount());
        }

        String message = String.format("""
                        %s
                        Proceeding with ekud.task removal directive...
                          deleted: %s
                        %s
                        """,
                completeResponse,
                removed,
                listStatus);
        ui.addToBuffer(message);

        storage.deleteTask(removed, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
