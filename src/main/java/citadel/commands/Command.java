package citadel.commands;

import citadel.exception.CitadelException;
import citadel.Task.TaskList;

/**
 * Represents an abstract command in the Citadel application.
 * Commands are executed to perform operations on the task list.
 * This class provides a common structure for all command types.
 */
public abstract class Command {

    /** The input string associated with the command. */
    public String input;

    /** The list of tasks that the command will operate on. */
    public TaskList tasks;

    /**
     * Constructs a new command with the specified input and task list.
     *
     * @param input The input string associated with the command.
     * @param tasks The task list that the command will operate on.
     */
    public Command(String input, TaskList tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Executes the command.
     * This method must be implemented by subclasses to define specific command behavior.
     *
     * @throws CitadelException If an error occurs during command execution.
     */
    public abstract void run() throws CitadelException;
}
