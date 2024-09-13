package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Creates an InvalidCommand.
     */
    public InvalidCommand() {
        super(false);
    }

    /**
     * Executes the invalid command.
     * Notifies the user that the command that they have typed in is invalid.
     *
     * @param tasklist The TaskList object used.
     * @param storage The Storage object used.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return "Invalid command, bozo.";
    }

}
