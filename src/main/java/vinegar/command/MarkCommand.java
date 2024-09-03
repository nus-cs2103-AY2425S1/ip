package vinegar.command;

import vinegar.task.TaskList;
import vinegar.Validator;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 2, "Please specify which task to mark.");
        try {
            index = Integer.parseInt(inputParts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new VinegarException("Task number must be a valid integer.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new VinegarException("Invalid task number. Please ensure the task number is correct and try again.");
        }

        tasks.getTask(index).markAsDone();
        ui.showMarked(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}
