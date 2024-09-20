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
     * Constructs a {@code ByeCommand} that prepares the application to exit.
     *
     * @param userInput The input string provided by the user.
     * @param tasks The list of tasks being managed in the application.
     * @param ui The user interface component responsible for displaying messages.
     */
    public ByeCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Indicates that this command results in exiting the application.
     *
     * @return {@code true} to indicate that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command, which prints a farewell message to the user.
     *
     * @return A farewell message to be shown to the user.
     */
    @Override
    public String execute() {
        String exitMessage = ui.getExitMessage();
        assert exitMessage != null : "Exit message should not be null";
        return exitMessage;
    }
}
