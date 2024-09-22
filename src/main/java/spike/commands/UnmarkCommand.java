package spike.commands;

import spike.exceptions.SpikeException;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param taskNumber Index of the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        assert taskNumber >= 0 : "Task number cannot be negative";
        this.taskIndex = taskNumber;
    }

    /**
     * Unmarks a task as done.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to save tasks to file.
     * @throws SpikeException If the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        try {
            assert tasks != null : "Task list cannot be null";
            tasks.markTaskUndone(taskIndex);
            assert ui != null : "User interface cannot be null";
            ui.showTaskUndone(tasks.getTaskString(taskIndex));
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
