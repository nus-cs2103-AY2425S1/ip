package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

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
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return "Bye. Hope to see you again soon!"
                + "Program will close now ...";
    }
}
