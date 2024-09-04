package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.TaskList;

import java.io.IOException;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws IOException {
        storage.saveLoadedTasks(tasks);
        ui.exit();
    }

    @Override
    public Command parse(String input) {
        return this;
    }
}
