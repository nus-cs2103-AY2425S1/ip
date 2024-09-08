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


    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * @return A farewell message to be shown to the user.
     */
    @Override
    public String execute() {
        return ui.getExitMessage();
    }
}
