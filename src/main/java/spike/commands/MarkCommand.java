package spike.commands;

import spike.storage.TaskList;
import spike.storage.Storage;
import spike.ui.Ui;
import spike.exceptions.SpikeException;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskNumber) {
        this.taskIndex = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        try {
            tasks.markTaskDone(taskIndex);
            ui.showTaskMarked(tasks.getTaskString(taskIndex));
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
