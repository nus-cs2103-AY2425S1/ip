package Tuesday.command;

import Tuesday.util.Storage;
import Tuesday.task.Task;
import Tuesday.util.Ui;

public class ListCommand extends Command{
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
