package tudee.command;

import tudee.task.TaskList;
import tudee.task.Task;
import tudee.ui.Ui;
import tudee.storage.Storage;
import tudee.TudeeException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException{
        if (index > tasks.size()) {
            throw new TudeeException("You do not have that many tasks!");
        }
        Task task = tasks.delete(index - 1);
        ui.showDelete(task, tasks.size());
        storage.save(tasks.get());
    }
}