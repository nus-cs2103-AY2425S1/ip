package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.TaskList;
import llama.ui.Ui;

/**
 * Represents to command to exit the program
 */
public class ExitCommand implements Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.displayBye();
        return false;
    }
}
