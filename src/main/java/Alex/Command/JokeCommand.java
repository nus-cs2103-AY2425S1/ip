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
        String joke = ui.showJoke();  // Assuming getJoke returns a joke as a String
        ui.appendMessage(joke);  // Append the joke to the UI output
    }
}

