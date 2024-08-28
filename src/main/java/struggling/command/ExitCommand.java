package struggling.command;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.save(tasks.getTasksState());
        ui.showGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
