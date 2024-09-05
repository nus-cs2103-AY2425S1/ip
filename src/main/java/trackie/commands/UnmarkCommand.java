package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }
    /**
     * Executes the unmark command.
     * This method will cause the tasklist to mark a task as not completed.
     * The user will be informed of the task's new status, which would be changed to
     * not completed after the unmarking operation.
     *
     * @param tasklist The TaskList object from which a task will be unmarked.
     * @param ui The Ui object used to display messages to the user.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.unmarkTask(arguments);
            storage.save();
        } catch (TrackieException e) {
            ui.displayErrorMessage(e);
        }
    }
}
