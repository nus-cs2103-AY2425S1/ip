package Commands;

import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

/**
 * Represents a bye command entered by the user.
 */
public class ByeCommand extends Command {
    /**
     * Stores the command string associated with bye command.
     *
     * @param command Command string.
     */
    public ByeCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot displays bye message and terminates.
     * </p>
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.displayResponse("Bye. Hope to see you again soon!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
