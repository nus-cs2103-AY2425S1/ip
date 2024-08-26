package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

/**
 * Represents a command to exit the application.
 * <p>
 * When executed, this command will display a farewell message to the user.
 * It also indicates that the application should terminate.
 * </p>
 */
public class ByeCommand extends Command {
    /**
     * Constructs a {@code ByeCommand} with the given argument.
     *
     * @param argument the argument provided to the command. This argument is not used for this command.
     */
    public ByeCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the {@code ByeCommand}.
     * <p>
     * This method will invoke the {@code display} method of the {@code Ui} class
     * to show a farewell message to the user.
     * </p>
     *
     * @param taskList the current list of tasks.
     * @param ui the user interface component that handles displaying messages to the user.
     *
     * @throws InvalidCommandException if the command cannot be executed.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        ui.display("Bye. Hope to see you again soon!\n");
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
