package samson.command;// Samson.Samson.Command.MarkCommand.java

import java.io.IOException;

import samson.Storage;
import samson.Ui;
import samson.task.*;
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index - 1; // Adjusting for zero-based indexing
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= taskList.size()) {
            ui.showTaskNumInvalid();
        }
        taskList.markTask(index);
        ui.showTaskMarked(taskList.get(index));
        storage.saveTasksToFile(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

