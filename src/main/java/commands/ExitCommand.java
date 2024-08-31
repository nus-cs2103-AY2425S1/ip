package commands;

import storage.Storage;
import taskList.TaskList;
import tasks.Task;
import ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
