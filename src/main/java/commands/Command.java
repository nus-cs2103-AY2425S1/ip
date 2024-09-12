package commands;

import cook.Storage;
import cook.TaskList;
import cook.Ui;

/**
 * Abstract Command class to common attributes and methods between concrete commands.
 */
public abstract class Command {
    protected String command;

    /**
     * Constructor for Command class.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Abstract execute method for Command class.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
