package mahesh.command;

import mahesh.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by printing the exit message.
     */
    public void execute() {
        Ui.printExitMessage();
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true as ExitCommand is an exit command
     */
    public boolean isExit() {
        return true;
    }

}
