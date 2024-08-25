package moimoi.command;

import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.MoiMoiException;

/**
 * Represents a task management command.
 */
public abstract class Command {

    private boolean isExit;

    public Command(boolean isExit){
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
     * @param ui MoiMoi's user interface.
     * @throws MoiMoiException If an error specific to MoiMoi occurs.
     */
    public abstract void execute(Storage storage, TaskList tasks, Ui ui) throws MoiMoiException;

}
