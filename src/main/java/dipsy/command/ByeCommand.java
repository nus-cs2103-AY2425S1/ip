package dipsy.command;

import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * Represents a command that handles exiting the application.
 * The {@code ByeCommand} is responsible for printing a farewell message and
 * terminating the program when executed. This command is issued when the
 * user wants to close the application.
 */
public class ByeCommand extends Command {

    /**
     * Represents a command to exit the application.
     * When executed, it will print an exit message and terminate the program.
     */
    public ByeCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Checks if the provided command is an instance of ByeCommand. It is used to check whether to exit the program
     * in the main loop polling for input in the PurfessorDipsy class.
     *
     * @param command The command to check.
     * @return {@code true} if the command is an instance of ByeCommand, {@code false} otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ByeCommand;
    }

    /**
     * Prints the exit message and terminates the program.
     */
    @Override
    public void execute() {
        ui.printExitMessage();
        System.exit(0);
    }
}
