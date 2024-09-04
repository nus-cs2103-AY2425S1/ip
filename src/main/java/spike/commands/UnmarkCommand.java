package spike.commands;

import spike.storage.TaskList;
import spike.storage.Storage;
import spike.ui.Ui;
import spike.exceptions.SpikeException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskNumber) {
        this.taskIndex = taskNumber;
    }

    @Override
    public String getCommandType() {
        return "Unmark";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        try {
            tasks.markTaskUndone(taskIndex);
            ui.showTaskUndone(tasks.getTaskString(taskIndex));
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
