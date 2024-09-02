package spike.commands;

import spike.exceptions.SpikeException;
import spike.tasks.Task;
import spike.storage.TaskList;
import spike.storage.Storage;
import spike.ui.Ui;

public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
        storage.writeToFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
