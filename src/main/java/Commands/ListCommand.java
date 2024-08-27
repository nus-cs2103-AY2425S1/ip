package Commands;

import Main.TaskList;
import UI.Ui;
import Storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasks(taskList);  // Display all tasks
    }
}
