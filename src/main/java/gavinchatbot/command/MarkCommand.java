package gavinchatbot.command;

import java.io.IOException;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.Ui;
import gavinchatbot.util.Storage;
import gavinchatbot.util.GavinException;

import java.io.IOException;

public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        try {
            tasks.markTask(index);
            ui.showMarkedTask(tasks.getTask(index));
            storage.save(tasks.getTasks());
        } catch (GavinException e) {
            ui.showError("An error occurred: " + e.getMessage());
        } catch (IOException e) {
            ui.showError("An error occurred while saving: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
