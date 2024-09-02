package mahesh.command;

/**
 * Represents an abstract command that can be executed and can indicate if it is an exit command.
 */
public abstract class Command {
    
    /**
     * Executes the command.
     */
    public abstract void execute();

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise
     */
    public abstract boolean isExit();
}

