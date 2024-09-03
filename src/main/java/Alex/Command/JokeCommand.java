package Alex.Command;

import Alex.Storage.Storage;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Command to print a joke using the Ui.
 */
public class JokeCommand extends CommandBase {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printJoke();
    }
}

