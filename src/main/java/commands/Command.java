package commands;

import cook.Storage;
import cook.TaskList;

/**
 * Abstract Command class to store common attributes and methods between Command objects.
 */
public abstract class Command {
    protected String commandName;

    /**
     * Constructs Command object.
     *
     * @param commandName Name of the command.
     */
    public Command(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Abstract execute method for Command class.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    public abstract String execute(TaskList tasks, Storage storage);

    /**
     * {inheritDoc}
     */
    @Override
    public boolean equals(Object command) {
        if (command instanceof Command) {
            return this.commandName.equals(((Command) command).commandName);
        }
        return false;
    }
}
