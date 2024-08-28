package barney.action.commands;

import java.util.HashMap;

import barney.action.CommandManager;
import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.exception.MissingFlagException;
import barney.ui.Ui;

/**
 * Represents an abstract command that can be executed.
 * 
 * This class provides a base implementation for different types of commands.
 * Subclasses must implement the {@link #execute(TaskList, Ui)} method to define
 * the specific behavior of the command.
 * 
 * @param name The name of the command.
 */
public abstract class Command {
    private final String name;

    /**
     * Constructs a new Command with the specified name.
     *
     * @param name the name of the command
     */
    public Command(String name) {
        this.name = name;
    }

    /**
     * Executes the command.
     *
     * @param tasks The task list to be modified by the command.
     * @param ui    The user interface to interact with the user.
     * @return {@code true} if the command is executed successfully, {@code false}
     *         otherwise.
     * @throws InvalidArgumentException if there is an invalid argument passed to
     *                                  the command.
     */
    public abstract boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException;

    /**
     * Verifies the flags in the given argument map.
     *
     * @param argumentMap the argument map containing the flags and their values
     * @throws InvalidArgumentException if any flag is missing or has an empty value
     */
    void verifyFlags(HashMap<String, String> argumentMap) throws InvalidArgumentException {
        for (String arg : CommandManager.CommandType.fromString(this.name).commandArgs) {
            if (!argumentMap.containsKey(arg)) {
                throw new MissingFlagException("Missing" + arg + " for " + name + "!");
            }
            if (argumentMap.get(arg).isEmpty()) {
                throw new InvalidArgumentException("The " + arg + " of a " + name + " cannot be empty!");
            }
        }
    }
}
