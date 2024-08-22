package wolfie.command;

import wolfie.exception.WolfieException;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String arguments) {
        this.index = Integer.parseInt(arguments) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        if (index < 0 || index >= tasks.size()) {
            throw new WolfieException("Invalid task number. Please use existing numbers and not the description.");
        }
        Task removedTask = tasks.remove(index);
        storage.save(tasks);
        ui.showTaskRemoved(removedTask, tasks.size());
    }
}