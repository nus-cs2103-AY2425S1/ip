package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a new DeleteCommand with the given arguments.
     *
     * @param arguments An array of Strings containing the command arguments.
     *                  Typically, this would include the index of the task to be deleted.
     */
    public DeleteCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    /**
     * Executes the delete command.
     * This method attempts to delete a task from the task list based on the provided arguments,
     * saves the updated task list. If the index provided is invalid, an exception will be thrown
     * and handled, causing an error message to be printed.
     *
     * @param tasklist The TaskList object from which a task will be deleted.
     * @param ui The Ui object used to display messages to the user.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.deleteTask(arguments);
            storage.save();
        } catch (TrackieException e) {
            ui.displayErrorMessage(e);
        }
    }
}
