package commands;

import cook.Storage;
import cook.TaskList;
import cook.Ui;

/**
 * Abstract Command class to common attributes and methods between concrete commands.
 */
public abstract class Command {
    protected String commandName;

    /**
     * Constructor for Command class.
     */
    public Command(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Abstract execute method for Command class.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * @inheritDoc.
     */
    @Override
    public boolean equals(Object command) {
        if (command instanceof Command) {
            return this.commandName.equals(((Command) command).commandName);
        }
        return false;
    }
}
