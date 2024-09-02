package samson.command;// Samson.Samson.Command.UnmarkCommand.java
import samson.Storage;
import samson.Ui;
import samson.task.*;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index - 1; // Adjusting for zero-based indexing
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= taskList.size()) {
            ui.showTaskNumInvalid();
        }
        taskList.unmarkTask(index);
        ui.showTaskUnmarked(taskList.get(index));
        storage.saveTasksToFile(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

