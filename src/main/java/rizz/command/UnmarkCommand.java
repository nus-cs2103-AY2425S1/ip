package rizz.command;

import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;
import java.io.IOException;


public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(taskIndex).unmarkAsDone();
            storage.saveTasks(tasks);  
            ui.unmarkTask(tasks.getTask(taskIndex));
        } catch (IOException e) {
            ui.showError("Unexpected error: " + e.getMessage());
        }
    }
}
