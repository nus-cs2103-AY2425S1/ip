package command;

import ui.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

import java.io.IOException;

// command.DeleteCommand.java
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.remove(index - 1);
            ui.showTaskDeleted(task, tasks.size());
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | IOException e) {
            ui.showSavingError();
        }
    }
}