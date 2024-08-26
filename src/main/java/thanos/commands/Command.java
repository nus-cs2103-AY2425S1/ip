package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

/**
 * Represents an abstract command.
 * <p>
 * The {@code Command} class provides the basic structure for all commands that can be executed within the application.
 * It holds an argument and defines the methods that subclasses must implement.
 * </p>
 */
public abstract class Command {
    /**
     * The argument associated with this command.
     * <p>
     * This argument can be used by subclasses to perform specific actions related to the command.
     * </p>
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
     * Executes the command with the provided {@code TaskList} and {@code Ui}.
     * <p>
     * This method must be implemented by subclasses to define the specific behavior of the command.
     * </p>
     *
     * @param taskList the current list of tasks
     * @param ui the user interface component that handles displaying messages to the user.
     *
     * @throws InvalidCommandException if the command cannot be executed.
     */
    public abstract void execute(TaskList taskList, Ui ui) throws InvalidCommandException;

    /**
     * Indicates whether this command signifies the exit of the application.
     * <p>
     * The default implementation returns {@code false}, indicating that this command does not cause the application to
     * exit. Subclasses can override this method to return {@code true} if the command is intended to terminate the
     * application.
     * </p>
     *
     * @return {@code true} if the command should exit the application; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
