package barney.action.commands;

import java.util.HashMap;

import barney.action.CommandManager;
import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.exception.MissingFlagException;
import barney.ui.Ui;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    private final String name;
    private final HashMap<String, String> argumentMap;

    /**
     * Constructs a new Command with the specified name.
     *
     * @param name the name of the command
     */
    public Command(String name, HashMap<String, String> argumentMap) {
        this.name = name;
        this.argumentMap = argumentMap;
    }

    /**
     * Executes the command.
     *
     * @param tasks The task list to be modified by the command.
     * @param ui    The user interface to interact with the user.
     * @return {@code true} if the command is executed successfully, {@code false}
     * otherwise.
     * @throws InvalidArgumentException if there is an invalid argument passed to
     *                                  the command.
     */
    public abstract boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException;

    /**
     * Verifies the flags in the given argument map.
     *
     * @throws InvalidArgumentException if any flag is missing or has an empty value
     */
    void verifyFlags() throws InvalidArgumentException {
        for (String arg : CommandManager.CommandType.fromString(this.name).commandArgs) {
            if (!argumentMap.containsKey(arg)) {
                throw new MissingFlagException("Missing" + arg + " for " + name + "!");
            }
            if (argumentMap.get(arg).isEmpty()) {
                throw new InvalidArgumentException("The " + arg + " of a " + name + " cannot be empty!");
            }
        }
    }

    /**
     * Returns the parameter value associated with the given key.
     *
     * @param key getting the value from the parameter map
     * @return the parameter value associated with the given key
     */
    String getParameter(String key) {
        return argumentMap.get(key);
    }
}
