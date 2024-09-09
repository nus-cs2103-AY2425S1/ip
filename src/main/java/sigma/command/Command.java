package sigma.command;

import sigma.exception.SigmaException;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the command to execute the user's input.
 */
public abstract class Command {

    protected String[] commandArray;

    public Command(String[] commandArray) {
        this.commandArray = commandArray;
    }

    /**
     * Executes the command.
     *
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
     *
     * @return String representation of the command.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
