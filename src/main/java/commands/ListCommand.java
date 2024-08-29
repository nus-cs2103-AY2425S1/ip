package commands;

import java.io.IOException;

import data.TaskList;
import data.Storage;
import ui.Ui;

/**
 * Represents the command to list all Tasks
 */
public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.listAllTasks(ui);
        return true;
    }
}
