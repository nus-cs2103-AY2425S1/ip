package mahesh.command;

/**
 * Represents an abstract command that can be executed and can indicate if it is an exit command.
 */
public abstract class Command {
    /**
     * Executes the command.
     */
    public abstract String execute();
}
