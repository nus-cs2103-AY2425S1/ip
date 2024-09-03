package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;
import Utility.Utility;

/**
 * Represents an unmark command entered by the user.
 */
public class UnmarkCommand extends Command {
    /**
     * Stores the command string associated with unmark command.
     *
     * @param command Command string.
     */
    public UnmarkCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if unmark command is valid.
     * If so, it unmarks the associated task in {@code tasks}.
     * Displays a response indicating it has successfully unmarked the task.
     * </p>
     *
     * @throws BrockException If unmark command is invalid.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        String command = super.getCommand();
        Utility.validateStatus(command, Utility.Action.UNMARK, tasks);

        int targetIndex = Utility.getTargetIndex(command);
        tasks.unmarkTask(targetIndex);
        ui.displayResponse("OK, I've marked this task as not done yet:\n"
                + "  "
                + tasks.getTaskDetails(targetIndex));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
