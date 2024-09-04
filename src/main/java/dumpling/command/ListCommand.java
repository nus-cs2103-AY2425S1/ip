package dumpling.command;

import dumpling.task.TaskList;
import dumpling.Ui.Ui;
import dumpling.Storage;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.echo(executeAndReturnLog(taskList, storage));
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) {
        return taskList.list();
    }

    public boolean isExit() {
        return false;
    }
}
