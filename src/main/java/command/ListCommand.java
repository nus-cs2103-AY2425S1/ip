package command;

import components.Storage;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

public class ListCommand extends Command{
    public ListCommand() {

    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LightException {
        ui.showMessage(TaskList.arrayToNumberedString(tasks));
    }
}
