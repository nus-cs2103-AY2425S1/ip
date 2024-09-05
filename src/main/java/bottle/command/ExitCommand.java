package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.exception.BottleException;
import bottle.task.TaskList;

/**
 * The type Exit command.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return ui.printByeMsg();
        } catch (BottleException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            System.exit(0);
        }
    }
}
