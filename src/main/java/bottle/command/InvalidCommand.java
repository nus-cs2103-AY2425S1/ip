package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.exception.BottleException;
import bottle.task.TaskList;

/**
 * The type Invalid command.
 */
public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Command is invalid!";
    }
}
