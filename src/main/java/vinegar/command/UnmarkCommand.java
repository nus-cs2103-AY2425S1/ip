package vinegar.command;

import vinegar.task.TaskList;
import vinegar.Validator;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 2, "Please specify which task to unmark.");
        this.index = Integer.parseInt(inputParts[1].trim()) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        tasks.getTask(index).markAsNotDone();
        ui.showUnmarked(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}
