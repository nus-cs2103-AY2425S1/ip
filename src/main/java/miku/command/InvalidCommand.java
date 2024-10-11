package miku.command;

import miku.utility.Response;
import miku.utility.Storage;
import miku.utility.TaskList;

/**
 * Represents an invalid command entered by the user.
 */
public class InvalidCommand extends Command {
    /**
     * Set the response for invalid commands.
     * @param taskList The task list that takes in the new task.
     * @param ui       The UI to perform printing.
     * @param storage  The storage function to store the data into a text file.
     */
    @Override
    public void execute(TaskList taskList, Response ui, Storage storage) {
        ui.setResponse("すみませんね、I do not understand your command.");
    }
}
