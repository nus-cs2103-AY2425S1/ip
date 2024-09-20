package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.exception.BottleException;
import bottle.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying a goodbye message and terminating the application.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving tasks
     * @return a goodbye message to the user
     */
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
