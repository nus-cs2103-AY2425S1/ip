package spike.commands;

import spike.storage.TaskList;
import spike.storage.Storage;
import spike.ui.Ui;
import spike.exceptions.SpikeException;
import spike.tasks.Task;

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        try {
            Task task = tasks.deleteTask(taskIndex);
            ui.showTaskDeleted(task, tasks.getSize());
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
