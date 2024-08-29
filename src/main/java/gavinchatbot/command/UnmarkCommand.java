package gavinchatbot.command;

import java.io.IOException;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.Ui;
import gavinchatbot.util.Storage;
import gavinchatbot.util.GavinException;

import java.io.IOException;

public class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        tasks.unmarkTask(index);
        ui.showUnmarkedTask(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
