package susan.command;

import susan.task.Task;
import susan.task.TaskList;
import susan.ui.Storage;
import susan.ui.SusanException;
import susan.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private String[] commandParts;

    public DeleteCommand(String[] commandParts) {
        this.commandParts = commandParts;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, SusanException {
        int taskIndex = Integer.parseInt(commandParts[1]) - 1;
        Task task = tasks.get(taskIndex);
        tasks.delete(taskIndex);
        storage.load(tasks);
        ui.showDeleteTask(task, tasks.size());
    }
}