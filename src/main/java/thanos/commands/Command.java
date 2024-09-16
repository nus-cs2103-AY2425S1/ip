package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.TaskList;

/**
 * Represents an abstract command that provides the basic structure for all commands that can be executed within
 * the application.
 */
public abstract class Command {
    /**
     * The argument associated with this command.
     */
    private final String argument;

    /**
     * Constructs a {@code Command} with the given argument.
     *
     * @param argument the argument associated with this command.
     */
    public Command(String argument) {
        this.argument = argument;
    }

    /**
     * Retrieves the argument associated with this command.
     *
     * @return the argument for this command.
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * Executes the command with the provided {@code TaskList}.
     *
     * @param taskList the current list of tasks
     * @return a string message resulting from the execution of the command.
     * @throws InvalidCommandException if the command cannot be executed.
     */
    public abstract String execute(TaskList taskList) throws InvalidCommandException;

    /**
     * Indicates whether this command signifies the exit of the application.
     *
     * @return {@code true} if the command should exit the application; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
