package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

/**
 * Represents a command that indicates an invalid user input.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command, returning an error message.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving tasks
     * @return an error message indicating that the command is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Command is invalid!";
    }
}
