package dumpling.command;

import dumpling.task.TaskList;
import dumpling.Ui;
import dumpling.Storage;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo(tasks.list());
    }

    public boolean isExit() {
        return false;
    }
}
