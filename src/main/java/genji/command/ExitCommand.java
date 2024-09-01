package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;
public class ExitCommand extends Command{

    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
