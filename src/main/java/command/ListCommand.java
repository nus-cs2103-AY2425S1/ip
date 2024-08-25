package command;

import java.io.IOException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}  
