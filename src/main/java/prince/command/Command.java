package prince.command;

import prince.exceptions.PrinceException;
import prince.tasks.TaskList;
import prince.util.Storage;
import prince.util.Ui;

/**
 * Command is an abstract class containing the
 * abstract method execute.
 */
public abstract class Command {

    private boolean isExit;

    /**
     * A constructor for the Command class.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns a boolean value.
     * @return Boolean value.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets isExit value to true.
     */
    protected void toggleIsExit() {
        this.isExit = true;
    }

    /**
     * Abstract method that executes a command based on the
     * input of the user. Requires overriding in subclasses.
     * @param tasks List of tasks.
     * @param ui Ui that was initialised in main.
     * @param storage Storage that was initialised in main.
     * @throws PrinceException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException;
}
