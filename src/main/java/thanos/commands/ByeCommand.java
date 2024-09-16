package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a {@code ByeCommand} with the given argument.
     *
     * @param argument the argument provided to the command.
     */
    public ByeCommand(String argument) {
        super(argument);
    }


    /**
     * Executes the {@code ByeCommand}, which provides a farewell message to indicates that the application is exiting.
     *
     * @param taskList The current list of tasks.
     * @return A string containing a farewell message for the user.
     * @throws InvalidCommandException This exception is not thrown by this method.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Indicates whether this command signifies the exit of the application.
     *
     * @return {@code true} as this command is used to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
