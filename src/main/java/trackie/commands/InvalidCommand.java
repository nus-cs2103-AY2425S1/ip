package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Executes the invalid command.
     * Notifies the user that the command that they have typed in is invalid.
     *
     * @param tasklist The TaskList object used.
     * @param storage The Storage object used.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return return "Invalid command m8. Maybe try typing \"help\" to see the list of available commands...";
    }

}
