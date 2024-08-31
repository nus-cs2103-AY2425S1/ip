package commands;

import storage.Storage;
import taskList.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * The caller must ensure to exit the program when they encounter this command using isExit().
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    /**
     * Execution actions:
     * - Print a goodbye message :)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
