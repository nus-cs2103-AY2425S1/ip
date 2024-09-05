package tuesday.command;

import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.Ui;

/**
 * Represents a command to list all the tasks in the list
 */
public class ListCommand extends Command {
    /**
     * Constructor for ListCommand
     *
     * @param command Description for the command
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Prints the tasks in the current list
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        ui.showList();
    }

    /**
     * Use to exit the program
     *
     * @return false and do not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
