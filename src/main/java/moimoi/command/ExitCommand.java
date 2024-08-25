package moimoi.command;

import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.MoiMoiException;
import moimoi.exception.StorageIOException;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a command to exit the program.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Saves the updated task list and exits the program.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @param ui MoiMoi's user interface.
     * @throws StorageIOException If an exception related to storage I/O occurs, during task list saving.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws StorageIOException {
        storage.save(tasks);
        ui.showExitMessage();
    }

}
