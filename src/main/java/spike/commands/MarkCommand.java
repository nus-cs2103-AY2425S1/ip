package spike.commands;

import spike.exceptions.SpikeException;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor for MarkCommand.
     *
     * @param taskNumber Index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        assert taskNumber >= 0 : "Task number cannot be negative";
        this.taskIndex = taskNumber;
    }

    /**
     * Marks the task as done and updates the storage file.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to update the storage file.
     * @throws SpikeException If the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        try {
            assert tasks != null : "Task list cannot be null";
            tasks.markTaskDone(taskIndex);
            assert ui != null : "User interface cannot be null";
            ui.showTaskMarked(tasks.getTaskString(taskIndex));
            assert storage != null : "Storage cannot be null";
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        }
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
