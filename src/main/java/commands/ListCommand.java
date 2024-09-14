package commands;

import cook.Storage;
import cook.TaskList;

/**
 * FindCommand class to process list commands.
 */
public class ListCommand extends Command {

    /**
     * Constructs ListCommand object.
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Lists all Task objects in tasks.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
