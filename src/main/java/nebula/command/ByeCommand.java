package nebula.command;

import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {

    public ByeCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        System.out.println(ui.goodbye());
        storage.saveTaskListToTextFile(TaskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
