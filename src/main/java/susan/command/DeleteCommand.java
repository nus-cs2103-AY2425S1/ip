package susan.command;

import susan.task.Task;
import susan.task.TaskList;
import susan.ui.Storage;
import susan.ui.SusanException;
import susan.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private String[] commandParts;

    public DeleteCommand(String[] commandDesc) {
        this.commandParts = commandDesc;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SusanException {
        try {
            int taskIndex = Integer.parseInt(commandParts[1]) - 1;
            Task task = tasks.get(taskIndex);
            tasks.delete(taskIndex);
            storage.load(tasks);
            ui.showDeleteTask(task, tasks.size());
        } catch (Exception e) {
            throw new SusanException("Please enter a valid task index to delete.");
        }
    }
}