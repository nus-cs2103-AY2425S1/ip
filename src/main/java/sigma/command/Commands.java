package sigma.command;

import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;
import sigma.exception.SigmaException;

/**
 * Represents the command to execute the user's input.
 */
public abstract class Commands {

    protected String[] split;
    public Commands(String[] split) {
        this.split = split;
    }

    /**
     * Executes the command.
     * @param tasks
     * @param ui
     * @param storage
     * @throws SigmaException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException;

    /**
     * Returns the string representation of the command.
     * @return String representation of the command.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
