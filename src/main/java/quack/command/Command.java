package quack.command;

/**
 * This is an abstract class to define a command object.
 */
public abstract class Command {

    /** Check if the task is complete or not */
    protected boolean isComplete;

    public Command() {
        this.isComplete = false;
    }

    /**
     * Retrieves the completion status of the command.
     * @return The boolean value of the command completion status.
     */
    public boolean getCompletionStatus() {
        return this.isComplete;
    }

    /**
     * Sets the completion status as done.
     */
    public void completeCommand() {
        this.isComplete = true;
    }

    /**
     * Prompts the user for inputs based on the command.
     */
    public abstract void prompt();

    /**
     * Executes the logic based on the specfic command.
     * @param input The input to be used during execution of the command.
     */
    public abstract void execute(String input);
}
