package dudu.command;

import java.io.IOException;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    private static final String invalidCommandString = "Invalid command. Please type help for the list of commands";

    /**
     * Returns a message notifying user of invalid command inputted.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return invalidCommandString;
    }

    /**
     * Checks if object is of type InvalidCommand.
     *
     * @param object Object to compare with.
     * @return True if object is an InvalidCommand object else false.
     */
    @Override
    public boolean equals(Object object) {
        return object instanceof InvalidCommand;
    }
}
