package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.TaskList;

/**
 * Command is an abstract class
 * It is used to execute the commands from the users.
 */
public abstract class Command {

    private final boolean isRunning;

    /**
     * Constructor for Command
     *
     * @param isRunning Signifies the running status of the program
     */
    public Command(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Is an abstract method which will be overridden in the child classes.
     * This method executes the intended action for the given command.
     *
     * @param taskList TaskList will be used by some of the child classes for their respective command
     * @throws InvalidTaskNumberException Is thrown when user inputs an invalid Task Number
     */
    public abstract void execute(TaskList taskList) throws InvalidTaskNumberException;
}
