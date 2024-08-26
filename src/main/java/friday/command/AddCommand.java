package friday.command;

import friday.Storage;
import friday.task.Task;
import friday.TaskList;
import friday.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.getSize());
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("An error occurred while saving the task.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
