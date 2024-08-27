package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listItems(taskList);
    }
}
