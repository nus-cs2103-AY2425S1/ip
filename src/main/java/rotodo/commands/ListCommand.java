package rotodo.commands;

import rotodo.processes.Storage;
import rotodo.processes.Ui;
import rotodo.tasklist.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {
        ui.addMessage(tl.toString());
    }
}
