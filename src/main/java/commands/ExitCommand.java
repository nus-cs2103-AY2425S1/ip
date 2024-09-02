package commands;

import java.io.IOException;

import data.Storage;
import data.TaskList;
import ui.Ui;

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
