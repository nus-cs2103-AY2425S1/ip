package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.TaskList;
import llama.ui.Ui;

/**
 * Represents the command to exit the program
 */
public class ExitCommand implements Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        return ui.displayBye();
    }
}
