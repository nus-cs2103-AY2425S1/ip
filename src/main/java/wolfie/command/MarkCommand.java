package wolfie.command;

import java.io.IOException;

import wolfie.exception.WolfieException;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String arguments) {
        this.index = Integer.parseInt(arguments) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        if (index < 0 || index >= tasks.size()) {
            throw new WolfieException("Invalid task number. Please use existing numbers and not the description.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks);
        ui.showTaskMarked(task);
    }
}
