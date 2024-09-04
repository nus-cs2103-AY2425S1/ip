package sigma.command;

import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;
import sigma.exception.SigmaException;

/**
 * Represents the command to execute the user's input.
 */
public abstract class Command {

    protected String[] split;
    public Command(String[] split) {
        this.split = split;
    }

    /**
     * Executes the command.
     * @param tasks
     * @param ui
     * @param storage
     * @throws SigmaException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException;

    public boolean isExit() {
        return false;
    }

    /**
     * Returns the string representation of the command.
     * @return String representation of the command.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
