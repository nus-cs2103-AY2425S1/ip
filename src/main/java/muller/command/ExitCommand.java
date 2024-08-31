package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
