package commands;

import java.io.IOException;

import data.TaskList;
import data.Storage;
import ui.Ui;

public class ExitCommand implements Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.displayBye();
        return false;
    }
}
