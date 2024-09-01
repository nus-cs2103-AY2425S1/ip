package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.ui.Ui;

/**
 * Represents a command to exit. Extends the {@link Command} class.
 */

public class EndCommand extends Command {

    /**
     * Represents an EndCommand that is used to exit the program.
     * <p>
     * This command is responsible for terminating the program execution. It
     * inherits from the Command class and accepts a HashMap of arguments. The "bye"
     * keyword is used to invoke this command.
     */
    public EndCommand(HashMap<String, String> argumentMap) {
        super("bye", argumentMap);
    }

    /**
     * Executes the EndCommand.
     * <p>
     * This method is responsible for executing the EndCommand. It verifies the
     * flags in the argument map and returns a boolean value.
     *
     * @param tasks The TaskList object containing the list of tasks.
     * @param ui    The Ui object for user interface interactions.
     * @return A boolean value indicating the success of the execution.
     * @throws InvalidArgumentException If the argument map is invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags();
        return false;
    }

}
