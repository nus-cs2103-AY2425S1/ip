package ekud.commands;

import ekud.exceptions.EkudException;
import ekud.components.Storage;
import ekud.components.Ui;
import ekud.task.Task;
import ekud.components.TaskList;

/**
 * Represents the {@link Command} to delete a {@link Task} at some index in a {@link TaskList}.
 *
 * @author uniqly
 */
public class DeleteCommand extends Command {
    /** The index to delete */
    private final int index;

    /** Creates a {@link DeleteCommand}.
     *
     * @param index The index to delete.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
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
        ui.printOutput(message);

        storage.deleteTask(removed, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
