package moimoi.util.command;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.exception.MoiMoiException;

/**
 * Represents a task management command.
 */
public abstract class Command {

    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command; false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @return Completion message or special response to be handled by the GUI.
     * @throws MoiMoiException If an error specific to MoiMoi occurs.
     */
    public abstract String execute(Storage storage, TaskList tasks) throws MoiMoiException;

}
