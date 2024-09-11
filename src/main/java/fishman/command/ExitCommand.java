package fishman.command;

import fishman.task.TaskList;
import fishman.utils.Ui;

/**
 * Represents a command to exit the program.
 * This command implements the Command interface and exits the program when
 * the specified keyword is entered and returns the termination message to
 * the user.
 */
public class ExitCommand implements Command {

    /**
     * @inheritDoc
     *
     *      Constructs a ExitCommand, which executes the exit command by returning
     *      the termination message to the user.
     *
     * @param taskList The TaskList, which is not used in this command but required by
     *              the interface.
     * @param ui The Ui instance used to display the termination message.
     * @return The termination message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return "exited";
    }

    /**
     * @inheritDoc
     *
     * @return true to indicate that the program should terminate
     *      after this command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
